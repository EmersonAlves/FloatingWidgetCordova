module.exports.open = function ({url, userId, driverId, token}, successCallback, errorCallback) {
    cordova.exec(() => {
            successCallback()
        }, () => {
            errorCallback()
        }, "FloatingWidget", "open",
        [{url, userId, driverId, token}]);
};

module.exports.close = function (successCallback, errorCallback) {
    cordova.exec(() => {
        successCallback()
    }, () => {
        errorCallback()
    }, "FloatingWidget", "close", []);
}

module.exports.getPermission = function (successCallback, errorCallback) {
    cordova.exec(() => {
        successCallback()
    }, () => {
        errorCallback()
    }, "FloatingWidget", "getPermission", []);
}