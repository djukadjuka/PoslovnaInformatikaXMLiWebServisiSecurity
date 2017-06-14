app.controller("role_permission_controller", function($scope,$http,$cookies,$location,$window,$controller){

    if($cookies.get('token')==null){
            window.location.href="#/login";
         }

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    $cookies.put('repositorium','role_permission');
    $cookies.put('subObjectsOne','role');
    $cookies.put('subObjectsTwo','permission');
    $cookies.put('subObjectsThree','tabela');
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
             $scope.obj.rolepermission_id=$(event.currentTarget).find(".id").html();

             i=$(event.currentTarget).find(".role_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.role_id === +i; })[ 0 ];
             $scope.obj.role=result;

             ii=$(event.currentTarget).find(".permission_id").html();
             var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.permission_id === +ii; })[ 0 ];
             $scope.obj.permission=resultt;

             iii=$(event.currentTarget).find(".tabela_id").html();
             var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.tabela_id === +iii; })[ 0 ];
             $scope.obj.tabela=resulttt;

         }
      };


      $scope.sync = function(item){
          if($scope.state=="edit"){
              $scope.obj={};
              $scope.obj.rolepermission_id=item.find(".id").html();

              i=item.find(".role_id").html();
              var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.role_id === +i; })[ 0 ];
              $scope.obj.role=result;

              ii=item.find(".permission_id").html();
              var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.permission_id === +ii; })[ 0 ];
              $scope.obj.permission=resultt;

              iii=item.find(".tabela_id").html();
              var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.tabela_id === +iii; })[ 0 ];
              $scope.obj.tabela=resulttt;
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

                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.role_id === +i; })[ 0 ];
                $scope.obj.role=result;
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

                var result=$scope.subObjectsTwo.filter(function( obj ) { return +obj.permission_id === +i; })[ 0 ];
                $scope.obj.permission=result;
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

                var result=$scope.subObjectsThree.filter(function( obj ) { return +obj.tabela_id === +i; })[ 0 ];
                $scope.obj.tabela=result;
            });
        });
});