app.controller('login_controller', function($rootScope, $scope, $http, $location,$q,$window,$cookies) {



  if($cookies.get("token")!=null){
    $scope.logged=true;
  }
  else{
    $scope.logged=false;
    window.location.href="#/login";
  }

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
            window.location.href="#/my_account";
            location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("nije proslo");
        }
    });
    };

  $scope.logout = function() {
    alert("klik na logout");
    $cookies.put("token",null);
    $cookies.put("uloga",null);
    $cookies.put('repositorium',null);
    $cookies.put('subObjectsOne',null);
    $cookies.put('subObjectsTwo',null);
    $cookies.put('subObjectsThree',null);
    $cookies.put('subObjectsFour',null);
    $scope.logged=false;
    window.location.href="#/login";
  }
});