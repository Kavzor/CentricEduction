var profileSearch = new Vue({
  el: "#profile",
  data: {
    placeholder: "Insert city search criteria",
    cityFilter: "",
    profiles: new Array()
  },
  methods: {
	  searchCity: function(event) {
	      axios
	        .get("http://localhost:8080/SpringExample/api/buddies/residences/" + this.cityFilter)
	        .then(response => this.profiles = response.data);
	  }
  }
});