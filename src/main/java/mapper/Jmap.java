package mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import annontation.FieldMap;
import annontation.FunctionMap;
import annontation.IgnoreField;

public class Jmap {
    private static final String IGNORED_FIELD = "this$0";
    private Object mapperFunctionsClass;

    /**
     * Set class with convert methods for complex mapping
     * @param mapperFunctionsClass
     * @return Jmap instance that work with the given convert methods
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Jmap with(Class mapperFunctionsClass) throws IllegalAccessException, InstantiationException {
        this.with(mapperFunctionsClass.newInstance());
        return this;
    }

    /**
     * Set class with convert methods for complex mapping
     * @param mapperFunctionsClass
     * @return Jmap instance that work with the given convert methods
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Jmap with(Object mapperFunctionsClass) {
        this.mapperFunctionsClass = mapperFunctionsClass;
        return this;
    }

    /**
     * Map source class to destination class
     * @param src source class to read fields value from
     * @param dst destination class to fields value to
     * @param <T> any class as destination class
     * @return destination mapped class
     * @throws Exception on any error with map processing
     */
    public <T> T map(Object src, Class<T> dst) throws IllegalAccessException, InstantiationException, NoSuchMethodException,
            InvocationTargetException, ClassNotFoundException, NoSuchFieldException {
        T dstClass = dst.newInstance();

        for (Field srcField : src.getClass().getDeclaredFields()) {
            if (!IGNORED_FIELD.equals(srcField.getName())) {
                setFieldProcess(src, srcField, dstClass);
            }
        }

        return dstClass;
    }

    /**
     * Make a field mapping process
     * @param srcClass source class to read
     * @param srcField source filed to read
     * @param dstClass destination class to write
     * @param <T> any class as destination class
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private <T>void setFieldProcess(Object srcClass, Field srcField, T dstClass) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Field dstField = null;
        boolean isFieldExist = true;

        try {
            dstField = getDstField(srcField, dstClass);
        } catch (NoSuchFieldException e) {
            // The destination field not exist
            isFieldExist = false;

            if (!isIgnoredField(srcClass, srcField)) {
                throw e;
            }
        }

        if (isFieldExist) {
            setField(srcClass, srcField, dstClass, dstField);
        }
    }

    /**
     * Set the destination fill by source field
     * @param srcClass source class to read
     * @param srcField source filed to read
     * @param dstClass destination class to write
     * @param dstField destination field to write
     * @param <T> any class as destination class
     * @throws IllegalAccessException on error with read field
     */
    private <T>void setField(Object srcClass, Field srcField, T dstClass, Field dstField)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        srcField.setAccessible(true);
        dstField.setAccessible(true);

        if (srcField.isAnnotationPresent(FunctionMap.class)) {
            if (mapperFunctionsClass != null) {
                Object val = mapperFunctionsClass.getClass()
                        .getMethod(srcField.getAnnotation(FunctionMap.class).value(), new Class[]{Class.forName(srcField.getGenericType().getTypeName())})
                        .invoke(mapperFunctionsClass, srcField.get(srcClass));

                dstField.set(dstClass, val);
            }
        } else {
            dstField.set(dstClass, srcField.get(srcClass));
        }
    }

    /**
     * Get the destination field to map according to source field
     * @param srcField source field to map
     * @param dstClass destination class to write to
     * @param <T> any class as destination class
     * @return the destination field to map
     * @throws NoSuchFieldException
     */
    private <T>Field getDstField(Field srcField, T dstClass) throws NoSuchFieldException {
        Field dstField;

        if (srcField.isAnnotationPresent(FieldMap.class)) {
            dstField = dstClass.getClass().getDeclaredField(srcField.getAnnotation(FieldMap.class).value());
        } else {
            dstField = dstClass.getClass().getDeclaredField(srcField.getName());
        }

        return dstField;
    }

    /**
     * Check whether field set to ignore or not
     * @param srcField source field to check
     * @return boolean indicate is ignored field
     */
    private boolean isIgnoredField(Field srcField) {
        boolean isIgnored = true;

        if (srcField.isAnnotationPresent(IgnoreField.class)) {
            if (!srcField.getAnnotation(IgnoreField.class).value()) {
                isIgnored = false;
            }
        }

        return isIgnored;
    }
}
