Vue.use(Vuetify)
Vue.use(VueChartJs.Line)

Vue.component('my-layout', {
	template: 
`<v-app id="inspire" dark>
    <v-navigation-drawer
      clipped
      fixed
      v-model="drawer"
      v-if="isLoginPage!=1"
      app
    >
      <v-list dense>
        <v-list-tile href="/dashboard">
          <v-list-tile-action>
            <v-icon>dashboard</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Dashboard</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile href="/room-weather">
          <v-list-tile-action>
            <v-icon>cloud_circle</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>Room weather</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
        <v-list-tile href="/city-weather">
          <v-list-tile-action>
            <v-icon>cloud</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>City weather</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-navigation-drawer>
    <v-toolbar app fixed clipped-left>
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title>SmartHouse</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn v-if="isLogged==1" icon href="/logout">
        <v-icon>exit_to_app</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
      <v-container fluid>
        <slot>Error</slot>
      </v-container>
    </v-content>
    <v-footer app fixed>
      <span>&copy; 2018</span>
    </v-footer>
</v-app>`,
	data: () => ({
	drawer: false
	}),
	props: {
	source: String,
	isLoginPage: Number,
	isLogged: Number
	}
});

Vue.component('dashboard', {
	template:
`<v-container grid-list-md text-xs-center fill-height>
    <v-layout row wrap style='max-width:100%;'>
      <v-flex xs12 md4>
      	<v-layout row wrap>
	      <v-flex xs12>
	        <v-card dark color="primary">
	          <v-card-text style="padding-bottom:2px;"><h1 class="dashboard-font">{{citytemp}} °C</h1></v-card-text>
	          <v-card-text style="padding-top:2px;"><h1 class="dashboard-font">in Bełchatów</h1></v-card-text>
	        </v-card>
	      </v-flex>
	      <v-flex xs12>
	        <v-card dark color="success">
	          <v-card-text style="padding-bottom:2px;"><h1 class="dashboard-font">{{temp}} °C</h1></v-card-text>
	          <v-card-text style="padding-top:2px;"><h1 class="dashboard-font">in room</h1></v-card-text>
	        </v-card>
	      </v-flex>
	      <v-flex xs12>
	        <v-card dark color="warning">
	          <v-card-text style="padding-bottom:2px;"><h1 class="dashboard-font">{{humidity}}%</h1></v-card-text>
	          <v-card-text style="padding-top:2px;"><h1 class="dashboard-font">humidity in room</h1></v-card-text>
	        </v-card>
	      </v-flex>
	    </v-layout>
      </v-flex>
      <v-flex xs12 md8>
		<line-chart></line-chart>
      </v-flex>
    </v-layout>
</v-container>`,
data(){
	return {
		citytemp: '- ',
		temp: '- ',
		humidity: '- '
	}
},
mounted(){
	if(window.cityWeather.length>0)
		this.citytemp = window.cityWeather[0].temp;
	
	if(window.roomWeather.length>0){
		this.temp = window.roomWeather[0].temp;
		this.humidity = window.roomWeather[0].humidity;
	}
}
});

Vue.component('room-weather', {
	template:
`<v-container grid-list-md text-xs-center fill-height>
    <v-layout row wrap style='max-width:100%;'>
      <v-flex xs12>
		<h1>Room Weather</h1>
      </v-flex>
      <v-flex xs12>
		  <v-data-table
		      v-bind:headers="headers"
		      :items="items"
		      hide-actions
		      class="elevation-1"
		      disable-initial-sort
		    >
		    <template slot="items" slot-scope="props">
		      <td>{{ props.item.temp }}</td>
		      <td>{{ props.item.humidity }}</td>
		      <td>{{ props.item.date }}</td>
		    </template>
		  </v-data-table>
      </v-flex>
    </v-layout>
</v-container>`,
data () {
return {
  headers: [
    { text: 'Temp (°C)', align: 'center', value: 'temp' },
	{ text: 'Humidity (out of 100%)', align: 'center', value: 'humidity' },
    {
        text: 'Measurement date',
        align: 'center',
        value: 'date'
      },
  ],
  items: [],
}
},
mounted(){
	var vm = this;
	axios.get('/api/get/lastMonthRoomWeather')
		.then(function(response){
			vm.items = response.data;
			
		})
		.catch(function(error){
			console.log(error);
		});
}
});

Vue.component('city-weather', {
	template:
`<v-container grid-list-md text-xs-center>
    <v-layout row wrap>
      <v-flex xs12>
		<h1>City Weather</h1>
      </v-flex>
      <v-flex xs12>
		  <v-data-table
		      v-bind:headers="headers"
		      :items="items"
		      hide-actions
		      class="elevation-1"
		      disable-initial-sort
		    >
		    <template slot="items" slot-scope="props">
		      <td>{{ props.item.temp }}</td>
		      <td>{{ props.item.windSpeed }}</td>
		      <td>{{ props.item.rainfall }}</td>
		      <td>{{ props.item.timestamp }}</td>
		    </template>
		  </v-data-table>
      </v-flex>
    </v-layout>
</v-container>`,
data () {
return {
  headers: [
    { text: 'Temp (°C)', align: 'center', sortable: false, value: 'temp' },
    { text: 'Wind speed (km/h)', align: 'center', value: 'windSpeed' },
    { text: 'Rainfall (mm)', align: 'center', value: 'rainfall' },
    {
        text: 'Measurement date',
        align: 'center',
        sortable: false,
        value: 'timestamp'
    },
  ],
  items: []
}
},
mounted(){
	var vm = this;
	axios.get('/api/get/lastMonthCityWeather')
		.then(function(response){
			vm.items = response.data;
		})
		.catch(function(error){
			console.log(error);
		});
}
});

Vue.component('line-chart', {
  extends: VueChartJs.Line,
  data: function(){
	  return {
		  chartOptions: 
		  {responsive: true,
		    	maintainAspectRatio: false,
		    	legend: {labels: {fontColor: '#ffffff'}},
		    	scales: {
		    		yAxes: [{
		    			ticks: {
		    				fontColor: '#ffffff'
		    			}
		    		}],
		    		xAxes: [{
		    			ticks: {
		    				fontColor: '#ffffff'
		    			}
		    		}]
		    	} 
		    }
	  }
  },
  mounted () {
	var wtr = window.roomWeather;
	var tempArray = [0,0,0,0,0,0,0,0];
	var humidityArray = [0,0,0,0,0,0,0,0];
	for(var i = 0; i<8; i++){
		if(wtr[7-i]!=null)
			tempArray[i]=wtr[7-i].temp
		if(wtr[7-i]!=null)
			humidityArray[i]=wtr[7-i].humidity
	}
    this.renderChart({
      labels: ['-7h-', '-6h', '-5h', '-4h', '-3h', '-2h', '-1h', 'now'],
      datasets: [
        {
          label: 'Room temp',
		  backgroundColor: 'rgba(0,0,0,0)',
          borderColor: 'rgba(76, 175, 80, 0.9)',
          borderWidth: 6,
          data: tempArray
        },
        {
		  label: 'Humidity in room',
		  backgroundColor: 'rgba(0,0,0,0)',
		  borderColor: 'rgba(255, 193, 7, 0.9)',
          borderWidth: 6,
		  data: humidityArray
		}
      ]
    }, this.chartOptions)
  },
    
})
