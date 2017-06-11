app.controller('login_controller', function($rootScope, $scope, $http, $location,$q,$window,$cookies) {

  $scope.credentials = {};

  $scope.login = function() {
    console.log($scope.credentials);
    var payload = {
        username: $scope.credentials.username,
        password: $scope.credentials.password
    };

    $.ajax({
        url: "/auth",
        type: "POST",
        data: JSON.stringify(payload),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            console.log("proslo");
            $cookies.put("token",data.token);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("nije proslo");
        }
    });
    };

  $scope.logout = function() {
    alert("klik na logout");
    $cookies.put("token",null);
  }
});