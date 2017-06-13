app.controller("business_partner_controller", function($scope,$http,$cookies,$location,$window,$controller,$filter){

     angular.extend(this, $controller('defaultController', {$scope: $scope}));

     $cookies.put('repositorium','business_partner');
     $cookies.put('subObjectsOne','company');
     $cookies.put('subObjectsTwo','company');
     $cookies.put('subObjectsThree',null);
     $cookies.put('subObjectsFour',null);

     if($cookies.get('state')==null || $cookies.get('state')==""){
        $cookies.put('state','edit');
     }

     $scope.setObjects();
     $scope.setSubObjects();
     $scope.setSubObjectsTwo();

     $scope.obj={};
     $scope.state=$cookies.get('state');

     $scope.foo = function(event, obj) {
        $(".highlighted").removeClass("highlighted");
        $(event.currentTarget).addClass("highlighted");
        if($scope.state=="edit"){
            $scope.obj={};
            $scope.obj.business_partner_id=$(event.currentTarget).find(".id").html();
            $scope.obj.type_of_bp=$(event.currentTarget).find(".type_of_bp").html();


            i=$(event.currentTarget).find(".company_id").html();
            var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company=result;

            i=$(event.currentTarget).find(".company_partner_id").html();
            var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company_partner=resultt;
        }
     }

     $scope.sync = function(item){
        if($scope.state=="edit"){
            $scope.obj={};
            $scope.obj.business_partner_id_id=item.find(".id").html();
            $scope.obj.type_of_bp=item.find(".type_of_bp").html();

            i=item.find(".company_id").html();
            var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company=result;

            i=item.find(".company_partner_id").html();
            var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company_partner=resultt;
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

    $scope.fookk = function(event, obj) {
         $("#highlightedTwo").removeClass("highlighted");
         $('#highlightedTwo').removeAttr('id');
         $(event.currentTarget).attr('id', 'highlightedTwo');
         $(event.currentTarget).addClass("highlighted");
      };

      $("#moPickup").off().on('click', function() {
        $scope.$apply(function() {
            i=$("#highlightedTwo").find(".sotid").html();
            $('#modalTwo').modal('toggle');

            var result=$scope.subObjectsTwo.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
            $scope.obj.company_partner=result;
        });
    });



});