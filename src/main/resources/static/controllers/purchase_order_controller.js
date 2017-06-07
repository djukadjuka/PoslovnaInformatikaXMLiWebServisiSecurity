app.controller("purchase_order_controller", function($scope,$http,$cookies,$location,$window,$controller){

    angular.extend(this, $controller('defaultController', {$scope: $scope}));

    if($cookies.get('repositorium')==null || $cookies.get('repositorium')=="" || $cookies.get('repositorium')!='purchase_order'){
       $cookies.put('repositorium','purchase_order');
    }
    if($cookies.get('subObjectsOne')==null || $cookies.get('subObjectsOne')=="" || $cookies.get('subObjectsOne')!='company'){
       $cookies.put('subObjectsOne','company');
    }
    if($cookies.get('subObjectsTwo')==null || $cookies.get('subObjectsTwo')=="" || $cookies.get('subObjectsTwo')!='business_partner'){
       $cookies.put('subObjectsTwo','business_partner');
    }
    if($cookies.get('subObjectsThree')==null || $cookies.get('subObjectsThree')=="" || $cookies.get('subObjectsThree')!='fiscal_year'){
       $cookies.put('subObjectsThree','fiscal_year');
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
      //$scope.$apply(function() {
        //$scope.countryChoosen=false;
        //});
      $scope.countryChoosen=true;
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
             var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.fiscal_year_id === +iii; })[ 0 ];
             $scope.obj.fiscal_year=resulttt;

         }
      };


      $scope.sync = function(item){
          $scope.$apply(function() {
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
                   var resulttt=$scope.subObjectsThree.filter(function( obj ) { return +obj.fiscal_year_id === +iii; })[ 0 ];
                   $scope.obj.fiscal_year=resulttt;
              }
          });
       };

       $scope.changed=function(objekat){
            if(objekat!=="undefined"){
            console.log($scope.objectsTwo);
                id=objekat.company_id;
                var result=$scope.objectsTwo.filter(function (el) {
                  return el.company.company_id==id;
                });
                $scope.subObjectsTwo=result;
                var resultt=$scope.objectsThree.filter(function (el) {
                  return el.company.company_id==id && el.active===true;
                });
                $scope.subObjectsThree=resultt;
                $scope.countryChoosen=false;

            }
            else{
                $scope.countryChoosen=true;
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
});