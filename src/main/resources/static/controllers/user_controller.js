app.controller("user_controller", function($scope,$http,$cookies,$location,$window,$controller){

    if($cookies.get('token')==null){
            window.location.href="#/login";
         }

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    $cookies.put('repositorium','user');
    $cookies.put('subObjectsOne','company');
    $cookies.put('subObjectsTwo','business_partner');
    $cookies.put('subObjectsThree','role');
    $cookies.put('subObjectsFour',null);

    if($cookies.get('state')==null || $cookies.get('state')==""){
       $cookies.put('state','edit');
    }

      $scope.setObjects();
      $scope.setSubObjects();
      $scope.setSubObjectsTwo();
      $scope.setSubObjectsThree();


      $scope.obj={};
      $scope.state=$cookies.get('state');


     $scope.foo = function(event, obj) {
         $(".highlighted").removeClass("highlighted");
         $(event.currentTarget).addClass("highlighted");
         if($scope.state=="edit"){
             $scope.obj={};
             $scope.obj.user_id=$(event.currentTarget).find(".id").html();
             $scope.obj.username=$(event.currentTarget).find(".username").html();
             $scope.obj.password=$(event.currentTarget).find(".password").html();

             i=$(event.currentTarget).find(".company_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
             $scope.obj.company=result;

             iii=$(event.currentTarget).find(".role_id").html();
             var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.role_id === +iii; })[ 0 ];
             $scope.obj.role=resulttt;

         }
      };


      $scope.sync = function(item){
          if($scope.state=="edit"){
              $scope.obj={};
              $scope.obj.purchase_order_id=item.find(".id").html();
              $scope.obj.username=item.find(".username").html();
              $scope.obj.password=item.find(".password").html();

               i=$item.find(".company_id").html();
               var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
               $scope.obj.company=result;

               iii=item.find(".role_id").html();
               var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.role_id === +iii; })[ 0 ];
               $scope.obj.role=resulttt;
          }
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

                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
                $scope.obj.company=result;
            });
        });

        $scope.fookkk = function(event, obj) {
             $("#highlightedThree").removeClass("highlighted");
             $('#highlightedThree').removeAttr('id');
             $(event.currentTarget).attr('id', 'highlightedThree');
             $(event.currentTarget).addClass("highlighted");
          };

          $("#mthPickup").off().on('click', function() {
            $scope.$apply(function() {
                i=$("#highlightedThree").find(".sothid").html();
                $('#modalThree').modal('toggle');

                var result=$scope.subObjectsThree.filter(function( obj ) { return +obj.role_id === +i; })[ 0 ];
                $scope.obj.role=result;
            });
        });
});