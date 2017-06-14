app.controller("vat_rate_controller", function($scope,$http,$cookies,$location,$window,$controller){

    if($cookies.get('token')==null){
            window.location.href="#/login";
         }

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    $cookies.put('repositorium','vat_rate');
    $cookies.put('subObjectsOne','vat_type');
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
             $scope.obj.vat_rate_id=$(event.currentTarget).find(".id").html();
             $scope.obj.date=new Date($(event.currentTarget).find(".date").html());
             $scope.obj.percentage_of_vatr=parseFloat($(event.currentTarget).find(".percentage").html());

             i=$(event.currentTarget).find(".vat_type_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.vat_type_id === +i; })[ 0 ];
             $scope.obj.vat_type=result;

         }
      };


      $scope.sync = function(item){
          if($scope.state=="edit"){
              $scope.obj={};
              $scope.obj.vat_rate_id=item.find(".id").html();
              $scope.obj.date=new Date(item.find(".date").html());
              $scope.obj.percentage_of_vatr=parseFloat(item.find(".percentage").html());

              i=item.find(".vat_type_id").html();
              var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.vat_type_id === +i; })[ 0 ];
              $scope.obj.vat_type=result;
          }
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