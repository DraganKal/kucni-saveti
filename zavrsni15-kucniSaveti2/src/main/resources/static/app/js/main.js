var wafepaApp = angular.module("wafepaApp",["ngRoute"]);

wafepaApp.controller("PorukeCtrl", function($scope, $http, $location){
	var url = "/api/poruke";
	var urlZgrade = "/api/zgrade";
	
	$scope.poruke = [];
	$scope.zgrade = [];
	
	$scope.poruka = {};
	$scope.poruka.naslov = "";
	$scope.poruka.tip = "";
	$scope.poruka.opis = "";
	$scope.poruka.potrebanProcenat = "";
	$scope.poruka.zgradaId = "";
	
	
	
	$scope.sParams = {};
	$scope.sParams.zgradaId = "";
	$scope.sParams.naslov = "";
	$scope.sParams.tip = "";
	
	$scope.pageNum = 0;
	$scope.totalPages = 1;
	
	var getPoruke = function(){			
			var config = {params:{}};
			
			if($scope.sParams.zgradaId != ""){
				config.params.zgradaId = $scope.sParams.zgradaId;
			}
			
			if($scope.sParams.naslov != ""){
				config.params.naslov = $scope.sParams.naslov;
			}
			if($scope.sParams.tip != ""){
				config.params.tip = $scope.sParams.tip;
			}
		
			config.params.pageNum = $scope.pageNum;
			
			var promise = $http.get(url, config);
			promise.then(
				function success(res){
					$scope.totalPages = res.headers("totalPages");
					$scope.poruke = res.data;
				},
				function error(){
					alert("Neuspesno dobavljanje poruka!");
				}
			);
	}	
	getPoruke();
	
	var getZgrade = function(){
		var promise = $http.get(urlZgrade);
		promise.then(
			function success(res){
				$scope.zgrade = res.data;
			},
			function error(res){
				alert("Neuspesno dobavljanje zgrada!");
			}
		);
	}
	getZgrade();
	
	$scope.changePage = function(direction){
		$scope.pageNum += direction;
		getPoruke();
	}
	
	$scope.doAdd = function(){
		$http.post(url, $scope.poruka).then(
			function success(res){
				$scope.poruka.naslov = "";
				$scope.poruka.tip = "";
				$scope.poruka.opis = "";
				$scope.poruka.potrebanProcenat = "";
				$scope.poruka.zgradaId = "";
				
				getPoruke();
		
			},
			function error(){
				alert("Neuspesno dodavanje poruke!");
			}
		);
	}
	
	
	
	
	$scope.doClear = function(){
		$scope.sParams.zgradaId = "";
		$scope.sParams.naslov = "";
		$scope.sParams.tip = "";
		getPoruke();
	}
	
	$scope.doObrisi = function(id){
		var promise = $http.delete(url + "/" + id);
		promise.then(
			function success(){
				getPoruke();
			},
			function error(){
				alert("Neuspesno brisanje poruka!");
			}
		);
	}
	
	$scope.goToEdit = function(id){
		$location.path("/poruke/edit/" + id);
	}
	
	
	$scope.doSearch = function(){
		$scope.pageNum = 0;
		getPoruke();
	}
	$scope.goToGlasaj = function(id){
		$location.path("/poruke/glasaj/" + id);
	}
	
	
});




wafepaApp.controller("EditPorukaCtrl", function($scope, $http, $routeParams, $location){
	
	var urlPoruka = "/api/poruke/" + $routeParams.id;
	var urlZgrade = "/api/zgrade";
	
	$scope.zgrade = [];

	$scope.poruka = {};
	$scope.poruka.naslov = "";
	$scope.poruka.tip = "";
	$scope.poruka.opis = "";
	$scope.poruka.potrebanProcenat = "";
	$scope.poruka.zgradaId = "";


	
	
	var getZgrade = function(){
		var promise = $http.get(urlZgrade);
		promise.then(
			function success(res){
				$scope.zgrade = res.data;
				getPoruka();
			},
			function error(res){
				alert("Neuspesno dobavljajne zgrada!");
			}
		);
	}
	
	var getPoruka = function(){
		$http.get(urlPoruka).then(
			function success(res){
				$scope.poruka = res.data;
			},
			function error(){
				alert("Neuspesno dobavljanje poruke!");
			}
		);
	}
	getZgrade();
	
	$scope.doEdit = function(){
		$http.put(urlPoruka, $scope.poruka).then(
			function success(){
				$location.path("/poruke");
			},
			function error(){
				alert("Nesto je poslo po zlu.");
			}
		);
	}
});


wafepaApp.controller("GlasajCtrl", function($scope, $http, $routeParams, $location){
	
	var url = "/api/poruke/glasaj/" + $routeParams.id;
	
	$scope.glas = {};
	$scope.glas.predlogPodrzan = "";
	
	$scope.doGlasaj = function(){
		$http.post(url, $scope.glas).then(
			function success(){
				$location.path("/poruke");
			},
			function error(){
				alert("Nesto je poslo po zlu.");
			}
		);
	}
	
});







wafepaApp.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/poruke.html',
			controller: "PorukeCtrl"
		})
		.when('/poruke', {
			templateUrl : '/app/html/poruke.html',
		})
		.when('/poruke/edit/:id', {
			templateUrl : '/app/html/edit-poruka.html'
		})
		.when('/poruke/glasaj/:id', {
			templateUrl : '/app/html/glasaj.html'
		})
		
		
		.otherwise({
			redirectTo: '/'
		});
}]);