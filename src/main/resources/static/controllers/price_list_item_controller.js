app.controller("price_list_item_controller", function($scope,$http,$cookies,$location,$window,$controller){

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    if($cookies.get('repositorium')==null || $cookies.get('repositorium')=="" || $cookies.get('repositorium')!='price_list_item'){
       $cookies.put('repositorium','price_list_item');
    }
    if($cookies.get('subObjectsOne')==null || $cookies.get('subObjectsOne')=="" || $cookies.get('subObjectsOne')!='item'){
       $cookies.put('subObjectsOne','item');
    }
    if($cookies.get('subObjectsTwo')==null || $cookies.get('subObjectsTwo')=="" || $cookies.get('subObjectsTwo')!='price_list'){
       $cookies.put('subObjectsTwo','price_list');
    }
    if($cookies.get('subObjectsThree')==null || $cookies.get('subObjectsThree')=="" || $cookies.get('subObjectsThree')!='currency'){
       $cookies.put('subObjectsThree','currency');
    }

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
      $scope.setSubObjectsThree();

      $scope.obj={};
      $scope.state=$cookies.get('state');


     $scope.foo = function(event, obj) {
         $(".highlighted").removeClass("highlighted");
         $(event.currentTarget).addClass("highlighted");
         if($scope.state=="edit"){
             $scope.obj={};
             $scope.obj.price_list_item_id=$(event.currentTarget).find(".id").html();
             $scope.obj.price=$(event.currentTarget).find(".price").html();

             i=$(event.currentTarget).find(".item_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.item_id === +i; })[ 0 ];
             $scope.obj.item=result;

             ii=$(event.currentTarget).find(".price_list_id").html();
             var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.price_list_id === +ii; })[ 0 ];
             $scope.obj.price_list=resultt;

             iii=$(event.currentTarget).find(".currency_id").html();
             var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.currency_id === +iii; })[ 0 ];
             $scope.obj.currency=resulttt;

         }
      };


      $scope.sync = function(item){
          $scope.$apply(function() {
              if($scope.state=="edit"){
                  $scope.obj={};
                  $scope.obj.price_list_item_id=item.find(".id").html();
                  $scope.obj.price=item.find(".price").html();

                   i=item.find(".item_id").html();
                   var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.item_id === +i; })[ 0 ];
                   $scope.obj.item=result;

                   ii=item.find(".price_list_id").html();
                   var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.price_list_id === +ii; })[ 0 ];
                   $scope.obj.price_list=resultt;

                   iii=item.find(".currency_id").html();
                   var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.currency_id === +iii; })[ 0 ];
                   $scope.obj.currency=resulttt;
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

                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.item_id === +i; })[ 0 ];
                $scope.obj.item=result;
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

                var result=$scope.subObjectsTwo.filter(function( obj ) { return +obj.price_list_id === +i; })[ 0 ];
                $scope.obj.price_list=result;
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

                var result=$scope.subObjectsThree.filter(function( obj ) { return +obj.currency_id === +i; })[ 0 ];
                $scope.obj.currency=result;
            });
        });
});