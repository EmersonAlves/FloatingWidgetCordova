/**
 * Constructor.
 *
 * @returns {FloatingWidget}
 */
function FloatingWidget() {

}


FloatingWidget.prototype.open = function (successCallback, errorCallback, config) {

    cordova.exec(
        function (result) {
            successCallback(result);
        },
        function (error) {
            errorCallback(error);
        },
        'FloatingWidget',
        'open',
        config
    );
};

//-------------------------------------------------------------------
FloatingWidget.prototype.getPermission = function (successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, 'FloatingWidget', 'getPermission', []);
}

if (!window.plugins.floatingWidget)
    window.plugins.floatingWidget = new FloatingWidget();