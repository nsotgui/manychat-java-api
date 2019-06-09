# ManyChat Java API 
This is a java implementation of the [ManyChat API](https://api.manychat.com/swagger#/).

[![Build Status](https://travis-ci.org/nsotgui/manychat-java-api.svg?branch=master)](https://travis-ci.org/nsotgui/manychat-java-api)  ![GitHub](https://img.shields.io/github/license/nsotgui/manychat-java-api.svg) ![Bintray](https://img.shields.io/bintray/v/nsotgui/manychat-java-api/manychat-api.svg?color=green&label=version)


## Table of Contents  
[Setup](#Setup)<br>
[Usage](#Usage)<br>
[Contributing](#Contributing)<br>
[License](#License)<br>

## Setup

Add the library to your build file.

_build.gradle_:
```groovy
...
repositories {
    maven {
      url 'https://dl.bintray.com/nsotgui/manychat-java-api/'
    }
}
...
dependencies {
    compile 'io.github.nsotgui:manychat-api:0.0.5'
}
```

## Usage

```java
// Instantiates the client
ManyChatAPIClient manyChatAPIClient = ManyChatAPIFactory.getManyChatAPIClient("<manychat api key>");

// Gets the custom fields
List<CustomField> customFields = manyChatAPIClient.getCustomFields();
for (CustomField field : customFields)
    System.out.println("Field: " + field);
```

See [_Example_](https://github.com/nsotgui/manychat-java-api/blob/master/manychat-api-example/src/main/java/io/github/nsotgui/simple/Application.java) for a simple example.

See [_Spring Framework_](https://github.com/nsotgui/manychat-java-api/tree/master/manychat-api-example/src/main/java/io/github/nsotgui/spring) for an example using the Spring Framework.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
This library is available under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0). 
