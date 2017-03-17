# jmap
java mapping objects library

## Quick Start
```java
Monkey monkey = new Monkey();

Human human = new Jmap().map(monkey, Hunman.class);
```
<br />

### Demo
**look the [test](https://github.com/urlvnbrg/jmap/tree/master/src/test/java/mapper) to see an example (include complex mapping)**

<br />

### complex mapping

#### Different Name
if the destination field have different name than the source field, you should use with `@FieldMap` annotation:
```java
class Monkey{
  @FieldMap("name")
  private String type;
}
```
<br />

#### Different Type
if the type of source field is different than destination field, you need to create helper class with method that convert the source field type to the destination field type.
```java
class HelperMap{
  public long convertPhone(String phone){
    return Long.valueOf(phone)
  }
}
```
notice: the parameter type is equal to source field type, and the return type is equal to destination field type<br />
in this example it's convert String to long
<br />

and on source field you have to use with `@FunctionMap` annotation to tell Jmap the convert method to use
```java
class Monkey{
  @FunctionMap("convertPhone")
  private String type;
}
```

and in the last step you need to tell Jamp to use with this helper class, it's done by `with` method
```java
new Jmap().with(HelperMap.class).map(monkey, Hunman.class);
```

#### Optional Fields
use `@IgnoreField` annotation to determine is Optional field or Required field<br />
you can put `@IgnoreField` annotation on the class to include all the fields with this setting

<br />

## API declaration
```java
Human human = new Jmap().map(monkey, Hunman.class);

Human human = new Jmap().with(HelperMap.class).map(monkey, Hunman.class);

@IgnoreField(false)
@IgnoreField(true)

@FieldMap("destinationFieldName")

@FunctionMap("helperMethodName")
```
