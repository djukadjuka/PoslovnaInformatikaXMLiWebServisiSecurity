app.controller('login_controller', function($rootScope, $scope, $http, $location,$q,$window,$cookies) {

  if($cookies.get("token")==null){
    $scope.logged=false;
    window.location.href="#/login";
  }
  else{
    $scope.logged=true;
    window.location.href="#/my_account";
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
    console.log($cookies);
    $.ajax({
            url: "/login?logout",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                /*$cookies.remove('token');
                $cookies.remove('repositorium');
                $cookies.remove('subObjectsOne');
                $cookies.remove('subObjectsTwo');
                $cookies.remove('subObjectsThree');
                $cookies.remove('subObjectsFour');*/
                $scope.logged=false;
                window.location.href="#/login";
                location.reload();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("nije proslo");
            }
        });
  }

  function createAuthorizationTokenHeader() {
          var token = $cookies.get("token");
          if (token) {
              return {"Authorization": token};
          } else {
              return {};
          }
      }
});