app.controller("fiscal_year_controller", function($scope,$http,$cookies,$location,$window,$controller,$filter){

     angular.extend(this, $controller('defaultController', {$scope: $scope}));

     $cookies.put('repositorium','fiscal_year');
     $cookies.put('subObjectsOne','company');
     $cookies.put('subObjectsTwo',null);
     $cookies.put('subObjectsThree',null);
     $cookies.put('subObjectsFour',null);

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
        if($scope.state=="edit"){
            $scope.obj={};
            $scope.obj.fiscal_year_id=item.find(".id").html();
            $scope.obj.number_of_fy=parseInt(item.find(".number_of_fy").html());
            $scope.obj.active=(item.find(".active").html()=="true");

            i=item.find(".company_id").html();
            var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company=result;
        }
     }

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