app.controller("vat_type_controller", function($scope,$http,$cookies,$location,$window,$controller,$filter){

     angular.extend(this, $controller('defaultController', {$scope: $scope}));

     if($cookies.get('repositorium')==null || $cookies.get('repositorium')=="" || $cookies.get('repositorium')!='vat_type'){
        $cookies.put('repositorium','vat_type');
     }

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
        $scope.$apply(function() {
            if($scope.state=="edit"){
                $scope.obj={};
                $scope.obj.vat_type_id=item.find(".id").html();
                $scope.obj.name=item.find(".name").html();
            }
        });
     }

    $("#nextform").click(function(){
        alert("kliknuto na next form");
        /*highlighted = $(".highlighted");
        id = highlighted.find(".id").html();
        //nextForm(id);
        myService.set(id);
        $window.location.href = '/#/settlement';*/
    });



});