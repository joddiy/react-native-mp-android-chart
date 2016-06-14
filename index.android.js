/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, { Component } from 'react';
import MPChart from 'mp-android-chart';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

var datasource = '{"entryCount":5,"setCount":3,"barType":"STACKED","xVals":["1990","1991","1992","1993","1994"],"dataSets":[{"yVals":[[33654.668,87116.97,59293.563],[12311.668,34121.97,52323.563],[55213.668,87682.97,67511.563],[64612.668,47231.97,67341.563],[51231.668,13467.97,85673.563]],"labels":["A","B","C"],"colors":["#4ebcda","#50c7a7","#dedede"]}]}';

class RnChartDemo extends Component {
  render() {
    return (
	<View style={styles.container}>
		<MPChart style={styles.chart} data_source={datasource}/>
	</View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  chart : {
	height : 250,
	width : 300,
	marginBottom:5,

  }
});

AppRegistry.registerComponent('RnChartDemo', () => RnChartDemo);
