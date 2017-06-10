app.controller("currency_controller", function($scope,$http,$cookies,$location,$window,$controller,$filter){

     angular.extend(this, $controller('defaultController', {$scope: $scope}));

     $cookies.put('repositorium','currency');
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
            $scope.obj.currency_id=$(event.currentTarget).find(".id").html();
            $scope.obj.name=$(event.currentTarget).find(".name").html();
            $scope.obj.abbreviation=$(event.currentTarget).find(".abbreviation").html();
        }
     }

     $scope.sync = function(item){
        $scope.$apply(function() {
            if($scope.state=="edit"){
                $scope.obj={};
                $scope.obj.currency_id=item.find(".id").html();
                $scope.obj.name=item.find(".name").html();
                $scope.obj.abbreviation=item.find(".abbreviation").html();
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