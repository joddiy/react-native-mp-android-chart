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


class RnChartDemo extends Component {
  render() {
    return (
	<View style={styles.container}>
		<Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
		<MPChart style={styles.chart}/>
		<Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
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
	width : 1000,
	marginBottom:5,

  },
  welcome:{
	height : 100,
	width : 200,
	backgroundColor: '#000000',
  }
});

AppRegistry.registerComponent('RnChartDemo', () => RnChartDemo);
