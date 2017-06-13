app.controller("purchase_order_controller", function($scope,$http,$cookies,$location,$window,$controller){

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    $cookies.put('repositorium','purchase_order');
    $cookies.put('subObjectsOne','company');
    $cookies.put('subObjectsTwo','business_partner');
    $cookies.put('subObjectsThree','fiscal_year');
    $cookies.put('subObjectsFour',null);

    if($cookies.get('state')==null || $cookies.get('state')==""){
       $cookies.put('state','edit');
    }

      $scope.setObjects();
      $scope.setSubObjects();
      $scope.setSubObjectsTwo();
      $scope.setSubObjectsThree();


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
             $scope.obj.purchase_order_id=$(event.currentTarget).find(".id").html();
             $scope.obj.date=new Date($(event.currentTarget).find(".date").html());
             $scope.obj.purchase_order_number=parseInt($(event.currentTarget).find(".purchase_order_number").html());

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
             var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.fiscal_year_id === +iii && obj.active==true; })[ 0 ];
             $scope.obj.fiscal_year=resulttt;

         }
      };


      $scope.sync = function(item){
          if($scope.state=="edit"){
              $scope.obj={};
              $scope.obj.purchase_order_id=item.find(".id").html();
              $scope.obj.date=new Date(item.find(".date").html());
              $scope.obj.purchase_order_number=parseInt(item.find(".purchase_order_number").html());

               i=$item.find(".company_id").html();
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
               var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.fiscal_year_id === +iii && obj.active==true; })[ 0 ];
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

        $scope.exportToXML=function(id){
            /*$http.post('/'+$cookies.get('repositorium')+'/exportToXML/'+id)
               .then(function(response){
                    alert("zavrsio");
               });*/

            $.ajax({
                url: '/'+$cookies.get('repositorium')+'/exportToXML/'+id,
                type: "POST",
                //data: object,
                contentType: "application/json; charset=utf-8",
                //dataType: "json",
                headers: $scope.createAuthorizationTokenHeader(),
                success: function (data, textStatus, jqXHR) {
                    console.log("proslo");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("nije proslo");
                }
            });
        };

        $scope.nextform = function() {
            highlighted = $(".highlighted");
            id = highlighted.find(".id").html();
            if(id!=null){
                $cookies.put("nextform_id",id);
                window.location.href = '/#/purchase_order_item';
            }
        };
});