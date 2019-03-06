# Example Cordova plugin for Android
When you realize, you are creating plugin to call one function on the android side it's ...

## How to install
Go to your cordova project and just copy paste the plugin there. Then run (in your project folder):

```
cordova plugin add ./cordova-example-plugin
```

You can have the plugin wherever you want, it doesn't have to be in you project path, but remember that may come time when someone else will be pulling your project from repository and he will have your plugin as a dependency.

## Call it
Then in javascript:
```
cordova.exec(function(success) {},                              //success callback
             function(error) {},                                //error callback
             "Example",                                         //class name
             "YOUR_ACTION_NAME_PARAMETER",                      //action name 
             ["Dog", "Pig", 42, false]);                        //args array
```
