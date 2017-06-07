app.controller('defaultController', ['$scope','$controller','$cookies','$http', function ($scope,$controller,$cookies,$http) {

  $("#first").off().on('click', function() {
           item = $("table tr:nth-child(2)");
           $(".highlighted").removeClass("highlighted");
           item.addClass("highlighted");
           $scope.sync(item);
           //$scope.sinhronizuj(item);
       });

   $("#last").off().on('click', function() {
        item = $("table tr:nth-last-child(1)");
        $(".highlighted").removeClass("highlighted");
        item.addClass("highlighted");
        $scope.sync(item);
        //$scope.sinhronizuj(item);
    });

    $("#next").off().on('click', function() {
          highlighted = $(".highlighted");
          var count = $("tr").length;
          if (count == 0)
              return;
          index =  $("tr").index(highlighted);
          if (index < 0)
              return;
          selectChild = 2;
          if (index < count - 1)
              selectChild = index + 2;
          item = $("tr:nth-child(" + selectChild + ")");
          $(".highlighted").removeClass("highlighted");
          item.addClass("highlighted");
          $scope.sync(item);
          //$scope.sinhronizuj(item);
      });

     $("#prev").off().on('click', function() {
          highlighted = $(".highlighted");
          var count = $("tr").length;
          if (count == 0)
              return;
          index =  $("tr").index(highlighted);
          if (index < 0)
              return;

          selectChild=index;

          if(index+1 == 2)
              selectChild=count;

          item = $("tr:nth-child(" + selectChild + ")");
          $(".highlighted").removeClass("highlighted");
          item.addClass("highlighted");
          $scope.sync(item);
          //$scope.sinhronizuj(item);
      });

      $("#add").off().on('click', function() {
           $scope.$apply(function() {
               $scope.obj={};
               $cookies.put('state','add');
               $scope.state=$cookies.get('state');
               $(".highlighted").removeClass("highlighted");
               $scope.countryChoosen=true;
               //$("#selekcija option:selected").prop("selected", false);
               //$("#selekcija option:first").prop("selected", true);
           });
       });

       $("#search").off().on('click', function() {
           $scope.$apply(function() {
               $scope.obj={};
               $cookies.put('state','search');
               $scope.state=$cookies.get('state');
               $(".highlighted").removeClass("highlighted");
               $scope.countryChoosen=true;
               //$("#selekcija option:selected").prop("selected", false);
               //$("#selekcija option:first").prop("selected", true);
           });
       });

       $("#rollback").off().on('click', function() {
           $scope.$apply(function() {
               $scope.obj={};
               $cookies.put('state','edit');
               $scope.state=$cookies.get('state');
               $(".highlighted").removeClass("highlighted");
               $scope.countryChoosen=true;
               //$("#selekcija option:selected").prop("selected", false);
               //$("#selekcija option:first").prop("selected", true);
           });
       });

       $("#remove").off().on('click', function() {
            highlighted = $(".highlighted");
            id = highlighted.find(".id").html();
            $http.delete('/'+$cookies.get('repositorium')+'/delete/'+id)
             .then(function(response){
                 $scope.obj = {};
                $scope.setObjects();

             });
            $scope.countryChoosen=true;
        });

        $scope.accept = function(object){
           if($scope.state=="add"){
              $http.post('/'+$cookies.get('repositorium')+'/create',object)
               .then(function(response){
                   $scope.obj = {};
                   $scope.setObjects();
               });
           }else if ($scope.state=="edit"){
              $http.put('/'+$cookies.get('repositorium')+'/update',object)
               .then(function(response){
                   $scope.obj = {};
                   $scope.setObjects();
               });
           }
           else if ($scope.state=="search"){
               $http.post('/'+$cookies.get('repositorium')+'/search',object)
                .then(function(response){
                    $scope.obj={};
                    $(".highlighted").removeClass("highlighted");
                    //$("#selekcija option:selected").prop("selected", false);
                    //$("#selekcija option:first").prop("selected", "selected");
                    $scope.objects=response.data;
                });
            }

            $scope.countryChoosen=true;
        };

        $scope.setObjects=function(){
            $http.get('/'+$cookies.get('repositorium'))
             .then(function(response){
                 $scope.objects = response.data;
             });
        };
        $scope.setSubObjects=function(){
            $http.get('/'+$cookies.get('subObjectsOne'))
                  .then(function(response){
                      $scope.subObjectsOne = response.data;
                      $scope.objectsOne=angular.copy($scope.subObjectsOne);
                  });
         }
         $scope.setSubObjectsTwo=function(){
             $http.get('/'+$cookies.get('subObjectsTwo'))
                   .then(function(response){
                       $scope.subObjectsTwo = response.data;
                       $scope.objectsTwo=angular.copy($scope.subObjectsTwo);
                   });
          }

          $scope.setSubObjectsThree=function(){
               $http.get('/'+$cookies.get('subObjectsThree'))
                     .then(function(response){
                         $scope.subObjectsThree = response.data;
                         $scope.objectsThree=angular.copy($scope.subObjectsThree);
                     });
            }

          $scope.setSubObjectsFour=function(){
             $http.get('/'+$cookies.get('subObjectsFour'))
                   .then(function(response){
                       $scope.subObjectsFour = response.data;
                       $scope.objectsFour=angular.copy($scope.subObjectsFour);
                   });
          }

}]);