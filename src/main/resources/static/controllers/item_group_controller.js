app.controller("item_group_controller", function($scope,$http,$cookies,$location,$window,$controller){

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    /*if($cookies.get('repositorium')==null || $cookies.get('repositorium')=="" || $cookies.get('repositorium')!='item_group'){
       $cookies.put('repositorium','item_group');
    }
    if($cookies.get('subObjectsOne')==null || $cookies.get('subObjectsOne')=="" || $cookies.get('subObjectsOne')!='vat_type'){
       $cookies.put('subObjectsOne','vat_type');
    }*/

    $cookies.put('repositorium','item_group');
    $cookies.put('subObjectsOne','vat_type');
    $cookies.put('subObjectsTwo',null);
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

      $scope.obj={};
      $scope.state=$cookies.get('state');


     $scope.foo = function(event, obj) {
         $(".highlighted").removeClass("highlighted");
         $(event.currentTarget).addClass("highlighted");
         if($scope.state=="edit"){
             $scope.obj={};
             $scope.obj.item_group_id=$(event.currentTarget).find(".id").html();
             $scope.obj.name=$(event.currentTarget).find(".name").html();

             i=$(event.currentTarget).find(".vat_type_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.vat_type_id === +i; })[ 0 ];
             $scope.obj.vat_type=result;

         }
      };


      $scope.sync = function(item){
          $scope.$apply(function() {
              if($scope.state=="edit"){
                  $scope.obj={};
                  $scope.obj.item_group_id=item.find(".id").html();
                  $scope.obj.name=item.find(".name").html();

                  i=item.find(".vat_type_id").html();
                  var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.vat_type_id === +i; })[ 0 ];
                  $scope.obj.vat_type=result;
              }
          });
       };

        $scope.fook = function(event, obj) {
             $("#highlightedOne").removeClass("highlighted");
             $('#highlightedOne').removeAttr('id');
             $(event.currentTarget).attr('id', 'highlightedOne');
             $(event.currentTarget).addClass("highlighted");
          };

          $("#vtPickup").off().on('click', function() {
            $scope.$apply(function() {
                i=$("#highlightedOne").find(".sooid").html();
                $('#modalOne').modal('toggle');

                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.vat_type_id === +i; })[ 0 ];
                $scope.obj.vat_type=result;
            });
        });
});