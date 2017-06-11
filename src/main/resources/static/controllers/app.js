var app = angular.module('app', ['ngRoute','ngResource','ngCookies','ngMessages']);
app.config(function($routeProvider,$locationProvider){
    $locationProvider.hashPrefix('');
    $routeProvider
        .when('/vat_type',{
            templateUrl:'/views/vat_type.html',
            controller: 'vat_type_controller'
        })
        .when('/vat_rate',{
            templateUrl:'/views/vat_rate.html',
            controller: 'vat_rate_controller'
        })
        .when('/item_group',{
            templateUrl:'/views/item_group.html',
            controller: 'item_group_controller'
        })
        .when('/units_of_measurement',{
            templateUrl:'/views/units_of_measurement.html',
            controller: 'units_of_measurement_controller'
        })
        .when('/item',{
            templateUrl:'/views/item.html',
            controller: 'item_controller'
        })
        .when('/price_list',{
            templateUrl:'/views/price_list.html',
            controller: 'price_list_controller'
        })
        .when('/currency',{
            templateUrl:'/views/currency.html',
            controller: 'currency_controller'
        })
        .when('/price_list_item',{
            templateUrl:'/views/price_list_item.html',
            controller: 'price_list_item_controller'
        })
        .when('/company',{
            templateUrl:'/views/company.html',
            controller: 'company_controller'
        })
        .when('/business_partner',{
            templateUrl:'/views/business_partner.html',
            controller: 'business_partner_controller'
        })
        .when('/fiscal_year',{
            templateUrl:'/views/fiscal_year.html',
            controller: 'fiscal_year_controller'
        })
        .when('/purchase_order',{
            templateUrl:'/views/purchase_order.html',
            controller: 'purchase_order_controller'
        })
        .when('/purchase_order_item',{
            templateUrl:'/views/purchase_order_item.html',
            controller: 'purchase_order_item_controller'
        })
        .when('/invoice',{
            templateUrl:'/views/invoice.html',
            controller: 'invoice_controller'
        })
        .when('/invoice_item',{
            templateUrl:'/views/invoice_item.html',
            controller: 'invoice_item_controller'
        })

        .when('/role',{
            templateUrl:'/views/role.html',
            controller: 'role_controller'
        })
        .when('/permission',{
            templateUrl:'/views/permission.html',
            controller: 'permission_controller'
        })
        .when('/role_permission',{
            templateUrl:'/views/role_permission.html',
            controller: 'role_permission_controller'
        })
        .when('/user',{
            templateUrl:'/views/user.html',
            controller: 'user_controller'
        })
        .when('/login',{
            templateUrl:'/views/login.html',
            controller: 'login_controller'
        })
        .when('/my_account',{
            templateUrl:'/views/my_account.html',
            controller: 'my_account_controller'
        })
        .otherwise(
            { redirectTo: '/login'}
        );
});