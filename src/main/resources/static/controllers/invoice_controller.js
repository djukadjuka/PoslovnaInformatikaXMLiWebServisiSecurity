app.controller("invoice_controller", function($scope,$http,$cookies,$location,$window,$controller){

    if($cookies.get('token')==null){
            window.location.href="#/login";
         }

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    $cookies.put('repositorium','invoice');
    $cookies.put('subObjectsOne','company');
    $cookies.put('subObjectsTwo','business_partner');
    $cookies.put('subObjectsThree','fiscal_year');
    $cookies.put('subObjectsFour','purchase_order');

    if($cookies.get('state')==null || $cookies.get('state')==""){
       $cookies.put('state','edit');
    }

      $scope.setObjects();
      $scope.setSubObjects();
      $scope.setSubObjectsTwo();
      $scope.setSubObjectsThree();
      $scope.setSubObjectsFour();


      $scope.obj={};
      var myDate=new Date();
      myDate.setDate(myDate.getDate()+1);
      $scope.myDate=myDate;
      $scope.state=$cookies.get('state');


     $scope.foo = function(event, obj) {
         $(".highlighted").removeClass("highlighted");
         $(event.currentTarget).addClass("highlighted");
         if($scope.state=="edit"){
             $scope.obj={};
             $scope.obj.invoice_id=$(event.currentTarget).find(".id").html();
             $scope.obj.date=new Date($(event.currentTarget).find(".date").html());
             $scope.obj.invoice_number=parseInt($(event.currentTarget).find(".invoice_number").html());
             $scope.obj.date_of_currency=new Date($(event.currentTarget).find(".date_of_currency").html());
             $scope.obj.total_tax_basis=parseFloat($(event.currentTarget).find(".total_tax_basis").html());
             $scope.obj.total_vat=parseFloat($(event.currentTarget).find(".total_vat").html());
             $scope.obj.total_price=parseFloat($(event.currentTarget).find(".total_price").html());
             $scope.obj.billing_account=$(event.currentTarget).find(".billing_account").html();
             $scope.obj.reference_number=$(event.currentTarget).find(".reference_number").html();

             i=$(event.currentTarget).find(".company_id").html();
             var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
             $scope.obj.company=result;


            var res=$scope.objectsTwo.filter(function (el) {
                  return el.company.company_id==i;
                });
             $scope.subObjectsTwo=res;
             ii=$(event.currentTarget).find(".business_partner_id").html();
             var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.business_partner_id === +ii; })[ 0 ];
             $scope.obj.business_partner=resultt;

             var ress=$scope.objectsThree.filter(function (el) {
                   return el.company.company_id==i && el.active===true;
                 });
              $scope.subObjectsThree=ress;
             iii=$(event.currentTarget).find(".fiscal_year_id").html();
             var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.fiscal_year_id === +iii; })[ 0 ];
             $scope.obj.fiscal_year=resulttt;

         }
      };


      $scope.sync = function(item){
          if($scope.state=="edit"){
              $scope.obj={};
              $scope.obj.purchase_order_id=item.find(".id").html();
              $scope.obj.date=new Date(item.find(".date").html());
              $scope.obj.invoice_number=parseInt(item.find(".invoice_number").html());
              $scope.obj.date_of_currency=new Date(item.find(".date_of_currency").html());
              $scope.obj.total_tax_basis=parseFloat(item.find(".total_tax_basis").html());
              $scope.obj.total_vat=parseFloat(item.find(".total_vat").html());
              $scope.obj.total_price=parseFloat(item.find(".total_price").html());
              $scope.obj.billing_account=item.find(".billing_account").html();
              $scope.obj.reference_number=item.find(".reference_number").html();

               i=item.find(".company_id").html();
               var result=$scope.subObjectsOne.filter(function( obj ) { return +obj.company_id === +i; })[ 0 ];
               $scope.obj.company=result;


              var res=$scope.objectsTwo.filter(function (el) {
                    return el.company.company_id==i;
                  });
               $scope.subObjectsTwo=res;
               ii=item.find(".business_partner_id").html();
               var resultt=$scope.subObjectsTwo.filter(function( obj ) { return +obj.business_partner_id === +ii; })[ 0 ];
               $scope.obj.business_partner=resultt;

               var ress=$scope.objectsThree.filter(function (el) {
                     return el.company.company_id==i && el.active===true;
                   });
                $scope.subObjectsThree=ress;
               iii=item.find(".fiscal_year_id").html();
               var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.fiscal_year_id === +iii; })[ 0 ];
               $scope.obj.fiscal_year=resulttt;
          }
       };

       $scope.$watch('obj.company', function (newValue, oldValue) {
           if(newValue!=oldValue){
               $scope.findBPandFY();
           }
       });
       $scope.findBPandFY=function(){
          if($scope.obj.company!=null){
               id=$scope.obj.company.company_id;
               var result=$scope.objectsTwo.filter(function (el) {
                 return el.company.company_id==id && el.type_of_bp!="supplier";
               });
               $scope.subObjectsTwo=result;
               var resultt=$scope.objectsThree.filter(function (el) {
                 return el.company.company_id==id && el.active==true;
               });
               $scope.subObjectsThree=resultt;
          }
          else{
               $scope.subObjectsTwo={};
               $scope.subObjectsThree={};
          }
      }

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

                var result=$scope.subObjectsTwo.filter(function( obj ) { return +obj.business_partner_id === +i; })[ 0 ];
                $scope.obj.business_partner=result;
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

                var result=$scope.subObjectsThree.filter(function( obj ) { return +obj.fiscal_year_id === +i; })[ 0 ];
                $scope.obj.fiscal_year=result;
            });
        });

        $scope.fookkkk = function(event, obj) {
             $("#highlightedFour").removeClass("highlighted");
             $('#highlightedFour').removeAttr('id');
             $(event.currentTarget).attr('id', 'highlightedFour');
             $(event.currentTarget).addClass("highlighted");
          };

          $("#mfPickup").off().on('click', function() {
            $scope.$apply(function() {
                i=$("#highlightedFour").find(".sofid").html();
                $('#modalFour').modal('toggle');

                /*$http.post('/'+$cookies.get('repositorium')+'/generateInvoice/'+i)
                   .then(function(response){
                        alert("zavrsio");
                   });*/
                $.ajax({
                    url: '/'+$cookies.get('repositorium')+'/generateInvoice/'+i,
                    type: "POST",
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
                //var result=$scope.subObjectsThree.filter(function( obj ) { return +obj.fiscal_year_id === +i; })[ 0 ];
                //$scope.obj.fiscal_year=result;
            });
        });

        $scope.nextform = function() {
            highlighted = $(".highlighted");
            id = highlighted.find(".id").html();
            if(id!=null){
                $cookies.put('nextform_id',id);
                window.location.href="#/invoice_item";
            }
        };

});