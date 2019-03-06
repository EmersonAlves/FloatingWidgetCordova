var exec = cordova.require("cordova/exec");

/**
 * Constructor.
 *
 * @returns {FloatingWidget}
 */
function FloatingWidget() {

}

FloatingWidget.prototype.open = function (successCallback, errorCallback, config) {

    exec(
        function(result) {
            successCallback(result);
        },
        function(error) {
            errorCallback(error);
        },
        'FloatingWidget',
        'open',
        config
    );
};

//-------------------------------------------------------------------
FloatingWidget.prototype.getPermission = function (successCallback, errorCallback) {
    exec(successCallback, errorCallback, 'FloatingWidget', 'getPermission', []);
};

var floatingWidget = new FloatingWidget();
module.exports = floatingWidget;