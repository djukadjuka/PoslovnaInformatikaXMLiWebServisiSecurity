app.controller("company_controller", function($scope,$http,$cookies,$location,$window,$controller,$filter){

    if($cookies.get('token')==null){
            window.location.href="#/login";
         }

     angular.extend(this, $controller('defaultController', {$scope: $scope}));

     $cookies.put('repositorium','company');
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
            $scope.obj.company_id=$(event.currentTarget).find(".id").html();
            $scope.obj.name=$(event.currentTarget).find(".name").html();
            $scope.obj.tin=parseInt($(event.currentTarget).find(".tin").html());
            $scope.obj.city=$(event.currentTarget).find(".city").html();
            $scope.obj.adress=$(event.currentTarget).find(".adress").html();
            $scope.obj.telephone=$(event.currentTarget).find(".telephone").html();
            $scope.obj.company_number=parseInt($(event.currentTarget).find(".company_number").html());
            $scope.obj.current_account=$(event.currentTarget).find(".current_account").html();
        }
     }

     $scope.sync = function(item){
        if($scope.state=="edit"){
            $scope.obj={};
            $scope.obj.company_id=item.find(".id").html();
            $scope.obj.name=item.find(".name").html();
            $scope.obj.tin=parseInt(item.find(".tin").html());
            $scope.obj.city=item.find(".city").html();
            $scope.obj.adress=item.find(".adress").html();
            $scope.obj.telephone=item.find(".telephone").html();
            $scope.obj.company_number=parseInt(item.find(".company_number").html());
            $scope.obj.current_account=item.find(".current_account").html();
        }
     }
});