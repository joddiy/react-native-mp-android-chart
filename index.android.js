/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */
'use strict';

var React = require('react-native');
var {
  AppRegistry,
  StyleSheet,
  Text,
  View,
} = React;
import {MPChart} from 'react-native-mp-android-chart'


var dataSource2 = {
    "entryCount": 5,
    "setCount": 3,
    "barType": "STACKED",
    "xVals": [
        "1990",
        "1991",
        "1992",
        "1993",
        "1994"
    ],
    "dataSets": [
        {
            "yVals": [
                [
                    33654.668,
                    87116.97,
                    59293.563
                ],
                [
                    12311.668,
                    34121.97,
                    52323.563
                ],
                [
                    55213.668,
                    87682.97,
                    67511.563
                ],
                [
                    64612.668,
                    47231.97,
                    67341.563
                ],
                [
                    51231.668,
                    13467.97,
                    85673.563
                ]
            ],
            "labels": [
                "A",
                "B",
                "C"
            ],
            "colors": [
                "#4ebcda",
                "#50c7a7",
                "#dedede"
            ]
        }
    ]
};

var dataSource3 ={

    "entryCount": 5,
    "setCount": 3,
    "xVals": [
        "1990",
        "1991",
        "1992",
        "1993",
        "1994"
    ],
    "dataSets": [
        {
            "yVals": [
                33654.668,
                87116.97,
                59293.563,
                99490.266,
                55637.76
            ],
            "label": "A",
            "colors": "#4ebcda",
        },
        {
            "yVals": [
                44444.97,
                33333.668,
                55555.266,
                66666.563,
                22222.76
            ],
            "label": "B",
            "colors": "#50c7a7"
        },
        {
            "yVals": [
                11111.668,
                22222.97,
                33333.563,
                44444.266,
                55555.76
            ],
            "label": "C",
            "colors": "#dedede"
        }
    ]

};

var dataSource4={
    "entryCount": 3,
    "xVals": [
        "A",
        "B",
        "C"
    ],
    "dataSets": [
        {
            "yVals": [
                0.12,
                0.38,
                0.5
            ],
            "colors": [
                "#4ebcda",
                "#50c7a7",
                "#dedede"
            ]
        }
    ]
};

var rn_mp_chart = React.createClass({
  render: function() {
    return (
      <View style={styles.container}>
          <View style={styles.part}>
                    <MPChart style={styles.chart}
                         type={'bar'}
                         dataSource={dataSource2}
                         chartStyles={{"animateType":"Y","animateY":1500}}
                         yAxis={{'position':'LEFT'}}
                         xAxis={{'position':'BOTTOM'}}
                    />
                    <MPChart style={styles.chart}
                         type={'bar'}
                         dataSource={dataSource3}
                         chartStyles={{"animateType":"Y","animateY":1500}}
                         yAxis={{'position':'LEFT'}}
                         xAxis={{'position':'BOTTOM'}}
                    />
          </View>
          <View style={styles.part}>
                    <MPChart style={styles.chart}
                          type={'line'}
                          dataSource={dataSource3}
                          chartStyles={{"animateType":"Y","animateY":1500}}
                          yAxis={{'position':'LEFT'}}
                          xAxis={{'position':'BOTTOM'}}
                    />
                    <MPChart style={styles.chart}
                          type={'pie'}
                          dataSource={dataSource4}
                          chartStyles={{"animateType":"Y","animateY":1500}}
                          yAxis={{'position':'LEFT'}}
                          xAxis={{'position':'BOTTOM'}}
                    />
                   </View>
      </View>
    );
  }
});

var styles = StyleSheet.create({
    container : {
        flex: 1,
        flexDirection : 'row',
        backgroundColor : '#bdbdbd',

    },
    part : {
        flex: 1,
        flexDirection : 'column',
        margin:10,
    },
    chart : {
        height : 250,
        marginBottom:5
    },
});

AppRegistry.registerComponent('rn_mp_chart', () => rn_mp_chart);
