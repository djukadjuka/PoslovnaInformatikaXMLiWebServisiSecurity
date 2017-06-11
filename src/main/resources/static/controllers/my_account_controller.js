app.controller("my_account_controller", function($scope,$http,$cookies,$location,$window,$controller){

    $scope.obj={};

    $.ajax({
        url: "/logged_user_model",
        type: "GET",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        headers: createAuthorizationTokenHeader(),
        success: function (data, textStatus, jqXHR) {
            console.log("proslo");
            console.log(data);
            $scope.$apply(function() {
                $scope.obj = data;
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("nije proslo");
        }
    });

    function createAuthorizationTokenHeader() {
        var token = $cookies.get("token");
        if (token) {
            return {"Authorization": token};
        } else {
            return {};
        }
    }

    $scope.changePassword=function(newPassword){
        var payload = {
                username: $scope.obj.username,
                password: newPassword
            };

        $.ajax({
                url: "/user/change_password/"+$scope.obj.username+"/"+newPassword,
                type: "POST",
                //data: JSON.stringify(payload),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                headers: createAuthorizationTokenHeader(),
                success: function (data, textStatus, jqXHR) {
                    console.log("proslo");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("nije proslo");
                }
            });
    }
});