app.controller('defaultController', ['$scope','$controller','$cookies','$http','$location', function ($scope,$controller,$cookies,$http,$location) {

  $("#first").off().on('click', function() {
           item = $("table tr:nth-child(2)");
           $(".highlighted").removeClass("highlighted");
           item.addClass("highlighted");
           $scope.sync(item);
       });

   $("#last").off().on('click', function() {
        item = $("table tr:nth-last-child(1)");
        $(".highlighted").removeClass("highlighted");
        item.addClass("highlighted");
        $scope.sync(item);
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
      });

      $("#add").off().on('click', function() {
           $scope.$apply(function() {
               $scope.obj={};
               $cookies.put('state','add');
               $scope.state=$cookies.get('state');
               $(".highlighted").removeClass("highlighted");
           });
       });

       $("#search").off().on('click', function() {
           $scope.$apply(function() {
               $scope.obj={};
               $cookies.put('state','search');
               $scope.state=$cookies.get('state');
               $(".highlighted").removeClass("highlighted");
           });
       });

       $("#rollback").off().on('click', function() {
           $scope.$apply(function() {
               $scope.obj={};
               $cookies.put('state','edit');
               $scope.state=$cookies.get('state');
               $(".highlighted").removeClass("highlighted");
           });
       });

       $("#remove").off().on('click', function() {
            highlighted = $(".highlighted");
            id = highlighted.find(".id").html();
            /*$http.delete('/'+$cookies.get('repositorium')+'/delete/'+id)
             .then(function(response){
                 $scope.obj = {};
                $scope.setObjects();

             });*/
             $.ajax({
                 url: '/'+$cookies.get('repositorium')+'/delete/'+id,
                 type: "DELETE",
                 //data: object,
                 contentType: "application/json; charset=utf-8",
                 //dataType: "json",
                 headers: $scope.createAuthorizationTokenHeader(),
                 success: function (data, textStatus, jqXHR) {
                     console.log("proslo");
                     $scope.$apply(function() {
                         $scope.obj={};
                         $scope.setObjects();
                     });
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     console.log("nije proslo");
                 }
             });
        });

        $("#refresh").off().on('click', function() {
            location.reload();
        });

        $scope.accept = function(object){
           if($scope.state=="add"){
              /*$http.post('/'+$cookies.get('repositorium')+'/create',object)
               .then(function(response){
                   $scope.obj = {};
                   $scope.setObjects();
               }).catch(function(error){
                    console.log(error.data);
               });*/
               $.ajax({
                   url: '/'+$cookies.get('repositorium')+'/create',
                   type: "POST",
                   data: JSON.stringify(object),
                   contentType: "application/json; charset=utf-8",
                   //dataType: "json",
                   headers: $scope.createAuthorizationTokenHeader(),
                   success: function (data, textStatus, jqXHR) {
                       console.log("proslo");
                       $scope.$apply(function() {
                           $scope.obj={};
                           $scope.setObjects();
                       });
                   },
                   error: function (jqXHR, textStatus, errorThrown) {
                       console.log("nije proslo");
                       console.log(textStatus);
                       console.log(errorThrown);
                   }
               });
           }else if ($scope.state=="edit"){
              /*$http.put('/'+$cookies.get('repositorium')+'/update',object)
               .then(function(response){
                   $scope.obj = {};
                   $scope.setObjects();
               }).catch(function(error){
                    console.log(error.data);
               });*/
               $.ajax({
                   url: '/'+$cookies.get('repositorium')+'/update',
                   type: "PUT",
                   data: JSON.stringify(object),
                   contentType: "application/json; charset=utf-8",
                   //dataType: "json",
                   headers: $scope.createAuthorizationTokenHeader(),
                   success: function (data, textStatus, jqXHR) {
                       console.log("proslo");
                       $scope.$apply(function() {
                           $scope.obj={};
                           $scope.setObjects();
                       });
                   },
                   error: function (jqXHR, textStatus, errorThrown) {
                       console.log("nije proslo");
                   }
               });
           }
           else if ($scope.state=="search"){
               /*$http.post('/'+$cookies.get('repositorium')+'/search',object)
                .then(function(response){
                    $scope.obj={};
                    $(".highlighted").removeClass("highlighted");
                    $scope.objects=response.data;
                }).catch(function(error){
                    console.log(error.data);
                });*/
               $.ajax({
                   url: '/'+$cookies.get('repositorium')+'/search',
                   type: "POST",
                   data: JSON.stringify(object),
                   contentType: "application/json; charset=utf-8",
                   dataType: "json",
                   headers: $scope.createAuthorizationTokenHeader(),
                   success: function (data, textStatus, jqXHR) {
                       console.log("proslo");
                       $(".highlighted").removeClass("highlighted");
                       $scope.$apply(function() {
                           $scope.obj={};
                           $scope.objects=data;
                       });
                   },
                   error: function (jqXHR, textStatus, errorThrown) {
                       console.log("nije proslo");
                   }
               });
            }
        };



        $scope.createAuthorizationTokenHeader= function(){
            var token = $cookies.get("token");
            if (token) {
                return {"Authorization": token};
            } else {
                return {};
            }
        }

        /*$scope.setObjects=function(){
            $http.get('/'+$cookies.get('repositorium'))
             .then(function(response){
                 $scope.objects = response.data;
             });
        };*/
        $scope.setObjects=function(){
            $.ajax({
                url: "/"+$cookies.get('repositorium'),
                type: "GET",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                headers: $scope.createAuthorizationTokenHeader(),
                success: function (data, textStatus, jqXHR) {
                    console.log("proslo");
                    $scope.$apply(function() {
                        $scope.objects = data;
                    });
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("nije proslo");
                }
            });
        };
        /*$scope.setSubObjects=function(){
            $http.get('/'+$cookies.get('subObjectsOne'))
                  .then(function(response){
                      $scope.subObjectsOne = response.data;
                      $scope.objectsOne=angular.copy($scope.subObjectsOne);
                  });
         }*/

         $scope.setSubObjects=function(){
             $.ajax({
                 url: "/"+$cookies.get('subObjectsOne'),
                 type: "GET",
                 contentType: "application/json; charset=utf-8",
                 dataType: "json",
                 headers: $scope.createAuthorizationTokenHeader(),
                 success: function (data, textStatus, jqXHR) {
                     console.log("proslo");
                     $scope.$apply(function() {
                         $scope.subObjectsOne = data;
                         $scope.objectsOne=angular.copy($scope.subObjectsOne);
                     });
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     console.log("nije proslo");
                 }
             });
          };

         /*$scope.setSubObjectsTwo=function(){
             $http.get('/'+$cookies.get('subObjectsTwo'))
                   .then(function(response){
                       $scope.subObjectsTwo = response.data;
                       $scope.objectsTwo=angular.copy($scope.subObjectsTwo);
                   });
          }*/

          $scope.setSubObjectsTwo=function(){
               $.ajax({
                    url: "/"+$cookies.get('subObjectsTwo'),
                    type: "GET",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    headers: $scope.createAuthorizationTokenHeader(),
                    success: function (data, textStatus, jqXHR) {
                        console.log("proslo");
                        $scope.$apply(function() {
                            $scope.subObjectsTwo = data;
                            $scope.objectsTwo=angular.copy($scope.subObjectsTwo);
                        });
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log("nije proslo");
                    }
                });
          };

          /*$scope.setSubObjectsThree=function(){
               $http.get('/'+$cookies.get('subObjectsThree'))
                     .then(function(response){
                         $scope.subObjectsThree = response.data;
                         $scope.objectsThree=angular.copy($scope.subObjectsThree);
                     });
            }*/

          $scope.setSubObjectsThree=function(){
             $.ajax({
                 url: "/"+$cookies.get('subObjectsThree'),
                 type: "GET",
                 contentType: "application/json; charset=utf-8",
                 dataType: "json",
                 headers: $scope.createAuthorizationTokenHeader(),
                 success: function (data, textStatus, jqXHR) {
                     console.log("proslo");
                     $scope.$apply(function() {
                         $scope.subObjectsThree = data;
                         $scope.objectsThree=angular.copy($scope.subObjectsThree);
                     });
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     console.log("nije proslo");
                 }
             });
          };

          /*$scope.setSubObjectsFour=function(){
             $http.get('/'+$cookies.get('subObjectsFour'))
                   .then(function(response){
                       $scope.subObjectsFour = response.data;
                       $scope.objectsFour=angular.copy($scope.subObjectsFour);
                   });
          }*/
          $scope.setSubObjectsFour=function(){
             $.ajax({
                 url: "/"+$cookies.get('subObjectsFour'),
                 type: "GET",
                 contentType: "application/json; charset=utf-8",
                 dataType: "json",
                 headers: $scope.createAuthorizationTokenHeader(),
                 success: function (data, textStatus, jqXHR) {
                     console.log("proslo");
                     $scope.$apply(function() {
                         $scope.subObjectsFour = data;
                         $scope.objectsFour=angular.copy($scope.subObjectsFour);
                     });
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                     console.log("nije proslo");
                 }
             });
          };


}]);