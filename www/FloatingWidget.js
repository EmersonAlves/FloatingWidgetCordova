const WidgetFloating = {
    open(){
        function success(){}
        function fail(){}

        cordova.exec(success, fail, "FloatingWidget", "sayHello", []);
    }
}