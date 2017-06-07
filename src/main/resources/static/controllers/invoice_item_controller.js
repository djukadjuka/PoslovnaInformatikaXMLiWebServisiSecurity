app.controller("invoice_item_controller", function($scope,$http,$cookies,$location,$window,$controller){

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    if($cookies.get('repositorium')==null || $cookies.get('repositorium')=="" || $cookies.get('repositorium')!='invoice_item'){
       $cookies.put('repositorium','invoice_item');
    }
    if($cookies.get('subObjectsOne')==null || $cookies.get('subObjectsOne')=="" || $cookies.get('subObjectsOne')!='invoice'){
       $cookies.put('subObjectsOne','invoice');
    }
    if($cookies.get('subObjectsTwo')==null || $cookies.get('subObjectsTwo')=="" || $cookies.get('subObjectsTwo')!='item'){
       $cookies.put('subObjectsTwo','item');
    }

    if($cookies.get('subObjectsThree')==null || $cookies.get('subObjectsThree')=="" || $cookies.get('subObjectsThree')!='price_list_item'){
       $cookies.put('subObjectsThree','price_list_item');
    }

    if($cookies.get('subObjectsFour')==null || $cookies.get('subObjectsFour')=="" || $cookies.get('subObjectsFour')!='vat_rate'){
       $cookies.put('subObjectsFour','vat_rate');
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
      $scope.setSubObjectsFour();

      $scope.invoiceChoosen=true;

      $scope.obj={};
      $scope.state=$cookies.get('state');


     $scope.foo = function(event, obj) {
         $(".highlighted").removeClass("highlighted");
         $(event.currentTarget).addClass("highlighted");
         if($scope.state=="edit"){
             $scope.obj={};
             $scope.obj.invoice_item_id=$(event.currentTarget).find(".id").html();
             $scope.obj.total_amount=parseInt($(event.currentTarget).find(".total_amount").html());
             $scope.obj.price=parseFloat($(event.currentTarget).find(".price").html());
             $scope.obj.discount=parseFloat($(event.currentTarget).find(".discount").html());
             $scope.obj.vat_basis=parseFloat($(event.currentTarget).find(".vat_basis").html());
             $scope.obj.vat_rate=parseFloat($(event.currentTarget).find(".vat_rate").html());
             $scope.obj.vat_amount=parseFloat($(event.currentTarget).find(".vat_amount").html());
             $scope.obj.total_price=parseFloat($(event.currentTarget).find(".total_price").html());

             i=$(event.currentTarget).find(".invoice_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.invoice_id === +i; })[ 0 ];
             $scope.obj.invoice=result;

             ii=$(event.currentTarget).find(".item_id").html();
             var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.item_id === +ii; })[ 0 ];
             $scope.obj.item=resultt;

         }
      };


      $scope.sync = function(item){
          $scope.$apply(function() {
              if($scope.state=="edit"){
                  $scope.obj={};
                  $scope.obj.invoice_item_id=item.find(".id").html();
                  $scope.obj.total_amount=parseInt(item.find(".total_amount").html());
                  $scope.obj.price=parseFloat(item.find(".price").html());
                  $scope.obj.discount=parseFloat(item.find(".discount").html());
                  $scope.obj.vat_basis=parseFloat(item.find(".vat_basis").html());
                  $scope.obj.vat_rate=parseFloat(item.find(".vat_rate").html());
                  $scope.obj.vat_amount=parseFloat(item.find(".vat_amount").html());
                  $scope.obj.total_price=parseFloat(item.find(".total_price").html());

                   i=item.find(".invoice_id").html();
                   var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.invoice_id === +i; })[ 0 ];
                   $scope.obj.invoice=result;

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

                var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.invoice_id === +i; })[ 0 ];
                $scope.obj.invoice=result;
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



        $scope.$watch('obj.invoice', function (newValue, oldValue) {
              if(newValue!=oldValue){
                  if(newValue!=null){
                      $scope.invoiceChoosen=false;
                  }
                  else{
                      $scope.obj.item={};
                      $scope.obj.price=0;
                      $scope.obj.vat_basis=0;
                      $scope.obj.vat_rate=0;
                      $scope.obj.vat_amount=0;
                      $scope.obj.total_price=0;
                      $scope.invoiceChoosen=true;
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

          $scope.$watch('obj.discount', function (newValue, oldValue) {
              if(newValue!=oldValue){
                  $scope.findPrice();
              }
          });


           $scope.findPrice=function(){
                console.log("usao");
                if($scope.obj.item!=null && $scope.obj.total_amount!=null && $scope.obj.discount!=null){
                    var datumFakure=$scope.obj.invoice.date;
                    var najbliziPLI=null;
                    for(var i=0 in $scope.subObjectsThree){
                        if($scope.obj.item.item_id==$scope.subObjectsThree[i].item.item_id){
                            if(datumFakure-$scope.subObjectsThree[i].price_list.valid_from>0){
                                if(najbliziPLI==null){
                                    najbliziPLI=$scope.subObjectsThree[i];
                                }
                                else{
                                    if(datumFakure-$scope.subObjectsThree[i].price_list.valid_from<datumFakure-najbliziPLI.price_list.valid_from){
                                        najbliziPLI=$scope.subObjectsThree[i];
                                    }
                                }
                            }
                        }
                    }
                    var najbliziVR=null;
                    for(var i=0 in $scope.subObjectsFour){
                        if($scope.obj.item.item_group.vat_type.vat_type_id==$scope.subObjectsFour[i].vat_type.vat_type_id){
                            console.log("USAO ")
                            if(najbliziVR==null){
                                najbliziVR=$scope.subObjectsFour[i];
                            }
                            else{
                                if(datumFakure-$scope.subObjectsFour[i].date<datumFakure-najbliziVR.date){
                                    najbliziVR=$scope.subObjectsFour[i];
                                }
                            }
                        }
                    }
                    $scope.obj.price=parseFloat(najbliziPLI.price);
                    $scope.obj.vat_basis=parseFloat(najbliziPLI.price-$scope.obj.discount);
                    $scope.obj.vat_rate=parseFloat(najbliziVR.percentage_of_vatr);
                    $scope.obj.vat_amount=parseFloat($scope.obj.vat_basis*parseFloat($scope.obj.vat_rate/100));
                    $scope.obj.total_price=($scope.obj.total_amount*$scope.obj.price)-$scope.obj.discount+$scope.obj.vat_amount;
                }else{
                    $scope.obj.price=0;
                    $scope.obj.vat_basis=0;
                    $scope.obj.vat_rate=0;
                    $scope.obj.vat_amount=0;
                    $scope.obj.total_price=0;
                }

           }
});