app.controller("vat_type_controller", function($scope,$http,$cookies,$location,$window,$controller,$filter){

    if($cookies.get('token')==null){
            window.location.href="#/login";
         }

     console.log('uloga je: '+$scope.uloga);

     angular.extend(this, $controller('defaultController', {$scope: $scope}));

     $cookies.put('repositorium','vat_type');
     $cookies.put('subObjectsOne',null);
     $cookies.put('subObjectsTwo',null);
     $cookies.put('subObjectsThree',null);
     $cookies.put('subObjectsFour',null);

     if($cookies.get('state')==null || $cookies.get('state')==""){
        $cookies.put('state','edit');
     }

     $scope.setObjects();
     $scope.obj={};
     $scope.state=$cookies.get('state');

     $scope.foo = function(event, obj) {
        $(".highlighted").removeClass("highlighted");
        $(event.currentTarget).addClass("highlighted");
        if($scope.state=="edit"){
            $scope.obj={};
            $scope.obj.vat_type_id=$(event.currentTarget).find(".id").html();
            $scope.obj.name=$(event.currentTarget).find(".name").html();
        }
     }

     $scope.sync = function(item){
        if($scope.state=="edit"){
            $scope.obj={};
            $scope.obj.vat_type_id=item.find(".id").html();
            $scope.obj.name=item.find(".name").html();
        }
     }

});