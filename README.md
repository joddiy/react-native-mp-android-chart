- the vesion in rn-0.26.02 is developing.
- the js of chart is int the dir: \node_modules\mp-android-chart
- you can easily to add a method of chart like this:
in MPAndroidBarChart
``` java
    /**
     * If set to true, all values are drawn above their bars, instead of below their top.
     *
     * @param mChart
     * @param enabled
     */
    @ReactProp(name = "draw_value_above_bar")
    public void setDrawValueAboveBar(BarChart mChart, Boolean enabled) {
        mChart.setDrawValueAboveBar(enabled);
    }
```
in mpchart.android.js
```js
/**
 *
 * @providesModule BarChart
 * @flow
 */
'use strict';

import { PropTypes } from 'react';
import { requireNativeComponent, View, TextInput } from 'react-native';

var BarChart ={
	name: 'BarChart',
	propTypes: {
		draw_highlight_arrow: PropTypes.bool,
		//something else
		...View.propTypes,
	},
};

module.exports =  requireNativeComponent('MPAndroidBarChart', BarChart);
```
