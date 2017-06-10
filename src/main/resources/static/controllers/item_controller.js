app.controller("item_controller", function($scope,$http,$cookies,$location,$window,$controller){

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    /*if($cookies.get('repositorium')==null || $cookies.get('repositorium')=="" || $cookies.get('repositorium')!='item'){
       $cookies.put('repositorium','item');
    }
    if($cookies.get('subObjectsOne')==null || $cookies.get('subObjectsOne')=="" || $cookies.get('subObjectsOne')!='item_group'){
       $cookies.put('subObjectsOne','item_group');
    }
    if($cookies.get('subObjectsTwo')==null || $cookies.get('subObjectsTwo')=="" || $cookies.get('subObjectsTwo')!='units_of_measurement'){
       $cookies.put('subObjectsTwo','units_of_measurement');
    }*/

    $cookies.put('repositorium','item');
    $cookies.put('subObjectsOne','item_group');
    $cookies.put('subObjectsTwo','units_of_measurement');
    $cookies.put('subObjectsThree',null);
    $cookies.put('subObjectsFour',null);

    if($cookies.get('state')==null || $cookies.get('state')==""){
       $cookies.put('state','edit');
    }

    /*if(myService.get()==null){
        $scope.setObjects();
     }else{
        $http.get('/settlements/searchByCountry/'+myService.get())
         .success(function(response){
             $scope.objects = response;
             myService.set(null);
         });
     }*/
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
             $scope.obj.item_id=$(event.currentTarget).find(".id").html();
             $scope.obj.name=$(event.currentTarget).find(".name").html();
             $scope.obj.description=$(event.currentTarget).find(".description").html();
             $scope.obj.is_service=($(event.currentTarget).find(".is_service").html()=="true");

             i=$(event.currentTarget).find(".item_group_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.item_group_id === +i; })[ 0 ];
             $scope.obj.item_group=result;

             ii=$(event.currentTarget).find(".units_of_measurement_id").html();
             var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.units_of_measurement_id === +ii; })[ 0 ];
             $scope.obj.units_of_measurement=resultt;

         }
      };


      $scope.sync = function(item){
          $scope.$apply(function() {
              if($scope.state=="edit"){
                  $scope.obj={};
                  $scope.obj.item_id=item.find(".id").html();
                  $scope.obj.name=item.find(".name").html();
                  $scope.obj.description=item.find(".description").html();
                  $scope.obj.is_service=(item.find(".is_service").html()=="true");

                  i=item.find(".item_group_id").html();
                  var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.item_group_id === +i; })[ 0 ];
                  $scope.obj.item_group=result;

                  ii=item.find(".units_of_measurement_id").html();
                  var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.units_of_measurement_id === +ii; })[ 0 ];
                  $scope.obj.units_of_measurement=resultt;
              }
          });
       };

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

                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.item_group_id === +i; })[ 0 ];
                $scope.obj.item_group=result;
            });
        });

        $scope.fookk = function(event, obj) {
             $("#highlightedTwo").removeClass("highlighted");
             $('#highlightedTwo').removeAttr('id');
             $(event.currentTarget).attr('id', 'highlightedTwo');
             $(event.currentTarget).addClass("highlighted");
          };

          $("#mtPickup").off().on('click', function() {
            $scope.$apply(function() {
                i=$("#highlightedTwo").find(".sotid").html();
                $('#modalTwo').modal('toggle');

                var result=$scope.subObjectsTwo.filter(function( obj ) { return +obj.units_of_measurement_id === +i; })[ 0 ];
                $scope.obj.units_of_measurement=result;
            });
        });
});