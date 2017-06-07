app.controller("fiscal_year_controller", function($scope,$http,$cookies,$location,$window,$controller,$filter){

     angular.extend(this, $controller('defaultController', {$scope: $scope}));

     if($cookies.get('repositorium')==null || $cookies.get('repositorium')=="" || $cookies.get('repositorium')!='fiscal_year'){
        $cookies.put('repositorium','fiscal_year');
     }
     if($cookies.get('subObjectsOne')==null || $cookies.get('subObjectsOne')=="" || $cookies.get('subObjectsOne')!='company'){
        $cookies.put('subObjectsOne','company');
     }

     if($cookies.get('state')==null || $cookies.get('state')==""){
        $cookies.put('state','edit');
     }

     $scope.setObjects();
     $scope.setSubObjects();

     $scope.obj={};
     $scope.state=$cookies.get('state');

     $scope.foo = function(event, obj) {
        $(".highlighted").removeClass("highlighted");
        $(event.currentTarget).addClass("highlighted");
        if($scope.state=="edit"){
            $scope.obj={};
            $scope.obj.fiscal_year_id=$(event.currentTarget).find(".id").html();
            $scope.obj.number_of_fy=parseInt($(event.currentTarget).find(".number_of_fy").html());
            $scope.obj.active=($(event.currentTarget).find(".active").html()=="true");

            i=$(event.currentTarget).find(".company_id").html();
            var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company=result;
        }
     }

     $scope.sync = function(item){
        $scope.$apply(function() {
            if($scope.state=="edit"){
                $scope.obj={};
                $scope.obj.fiscal_year_id=item.find(".id").html();
                $scope.obj.number_of_fy=parseInt(item.find(".number_of_fy").html());
                $scope.obj.active=(item.find(".active").html()=="true");

                i=item.find(".company_id").html();
                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
                $scope.obj.company=result;
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


    $scope.fook = function(event, obj) {
         $("#highlightedOne").removeClass("highlighted");
         $('#highlightedOne').removeAttr('id');
         $(event.currentTarget).attr('id', 'highlightedOne');
         $(event.currentTarget).addClass("highlighted");
      };

      $("#moPickup").off().on('click', function() {
        $scope.$apply(function() {
            i=$("#highlightedOne").find(".sooid").html();
            $('#modalOne').modal('toggle');

            var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company=result;
        });
    });



});