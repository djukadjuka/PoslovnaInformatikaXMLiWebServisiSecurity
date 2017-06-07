app.controller("purchase_order_item_controller", function($scope,$http,$cookies,$location,$window,$controller){

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    if($cookies.get('repositorium')==null || $cookies.get('repositorium')=="" || $cookies.get('repositorium')!='purchase_order_item'){
       $cookies.put('repositorium','purchase_order_item');
    }
    if($cookies.get('subObjectsOne')==null || $cookies.get('subObjectsOne')=="" || $cookies.get('subObjectsOne')!='purchase_order'){
       $cookies.put('subObjectsOne','purchase_order');
    }
    if($cookies.get('subObjectsTwo')==null || $cookies.get('subObjectsTwo')=="" || $cookies.get('subObjectsTwo')!='item'){
       $cookies.put('subObjectsTwo','item');
    }

    if($cookies.get('subObjectsThree')==null || $cookies.get('subObjectsThree')=="" || $cookies.get('subObjectsThree')!='price_list_item'){
       $cookies.put('subObjectsThree','price_list_item');
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
      $scope.purchaseOrderChoosen=true;

      $scope.obj={};
      $scope.state=$cookies.get('state');


     $scope.foo = function(event, obj) {
         $(".highlighted").removeClass("highlighted");
         $(event.currentTarget).addClass("highlighted");
         if($scope.state=="edit"){
             $scope.obj={};
             $scope.obj.purchase_order_item_id=$(event.currentTarget).find(".id").html();
             $scope.obj.total_amount=parseInt($(event.currentTarget).find(".total_amount").html());
             $scope.obj.total_price=parseFloat($(event.currentTarget).find(".total_price").html());

             i=$(event.currentTarget).find(".purchase_order_id").html();
             console.log(i);
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.purchase_order_id === +i; })[ 0 ];
             console.log(result);
             $scope.obj.purchase_order=result;

             ii=$(event.currentTarget).find(".item_id").html();
             console.log(ii);
             var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.item_id === +ii; })[ 0 ];
             console.log(resultt);
             $scope.obj.item=resultt;

         }
      };


      $scope.sync = function(item){
          $scope.$apply(function() {
              if($scope.state=="edit"){
                  $scope.obj={};
                  $scope.obj.purchase_order_item_id=item.find(".id").html();
                  $scope.obj.total_amount=parseInt(item.find(".total_amount").html());
                  $scope.obj.total_price=parseFloat(item.find(".total_price").html());

                   i=item.find(".purchase_order_id").html();
                   var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.purchase_order_id === +i; })[ 0 ];
                   $scope.obj.purchase_order=result;

                   ii=item.find(".item_id").html();
                   var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.item_id === +ii; })[ 0 ];
                   $scope.obj.item=resultt;
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

                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.purchase_order_id === +i; })[ 0 ];
                $scope.obj.purchase_order=result;
                //$scope.$digest();
                //$("#selectOne").changed($scope.obj.purchase_order);
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

                var result=$scope.subObjectsTwo.filter(function( obj ) { return +obj.item_id === +i; })[ 0 ];
                $scope.obj.item=result;

            });
        });


      $scope.$watch('obj.purchase_order', function (newValue, oldValue) {
          if(newValue!=oldValue){
              if(newValue!=null){
                  $scope.purchaseOrderChoosen=false;
              }
              else{
                  $scope.obj.item={};
                  $scope.obj.total_price=0;
                  $scope.purchaseOrderChoosen=true;
              }
              $scope.findPrice();
          }
      });

      $scope.$watch('obj.item', function (newValue, oldValue) {
            if(newValue!=oldValue){
                $scope.findPrice();
            }
        });

      $scope.$watch('obj.total_amount', function (newValue, oldValue) {
            if(newValue!=oldValue){
                $scope.findPrice();
            }
        });


       $scope.findPrice=function(){
            if($scope.obj.item!=null && $scope.obj.total_amount!=null){
                var datumNarudzbenice=$scope.obj.purchase_order.date;
                var najbliziPLI=null;
                for(var i=0 in $scope.subObjectsThree){
                    if($scope.obj.item.item_id==$scope.subObjectsThree[i].item.item_id){
                        if(datumNarudzbenice-$scope.subObjectsThree[i].price_list.valid_from>0){
                            if(najbliziPLI==null){
                                najbliziPLI=$scope.subObjectsThree[i];
                            }
                            else{
                                if(datumNarudzbenice-$scope.subObjectsThree[i].price_list.valid_from<datumNarudzbenice-najbliziPLI.price_list.valid_from){
                                    najbliziPLI=$scope.subObjectsThree[i];
                                }
                            }
                        }
                    }
                }
                $scope.obj.total_price=parseFloat($scope.obj.total_amount*najbliziPLI.price);
            }else{
                $scope.obj.total_price=0;
            }

       }
});