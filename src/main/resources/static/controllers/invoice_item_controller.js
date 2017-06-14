app.controller("invoice_item_controller", function($scope,$http,$cookies,$location,$window,$controller){

    if($cookies.get('token')==null){
            window.location.href="#/login";
         }

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    $cookies.put('repositorium','invoice_item');
    $cookies.put('subObjectsOne','invoice');
    $cookies.put('subObjectsTwo',null);
    /*$http.get('/item/findAllValid')
       .then(function(response){
           console.log(response);
           $scope.subObjectsTwo = response.data;
           $scope.objectsTwo=angular.copy($scope.subObjectsTwo);
       });*/
    $.ajax({
        url: '/item/findAllValid',
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

    $cookies.put('subObjectsThree','price_list_item');
    $cookies.put('subObjectsFour','vat_rate');


    if($cookies.get('state')==null || $cookies.get('state')==""){
       $cookies.put('state','edit');
    }

      if($cookies.get('nextform_id')==null || $cookies.get('nextform_id')=='undefined'){
        $scope.setObjects();
      }else{
        $.ajax({
            url: '/'+$cookies.get('subObjectsOne')+'/allIIs/'+$cookies.get('nextform_id'),
            type: "POST",
            //data: object,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            headers: $scope.createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                console.log("proslo");
                $scope.$apply(function() {
                    $scope.objects = data;
                    $cookies.put("nextform_id",'undefined');
                });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("nije proslo");
            }
        });
      }
      $scope.setSubObjects();
      //$scope.setSubObjectsTwo();
      $scope.setSubObjectsThree();
      $scope.setSubObjectsFour();

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
                  $scope.obj.item={};
                  $scope.obj.price=0;
                  $scope.obj.vat_basis=0;
                  $scope.obj.vat_rate=0;
                  $scope.obj.vat_amount=0;
                  $scope.obj.total_price=0;
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