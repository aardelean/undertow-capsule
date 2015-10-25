'use strict';
var pointsUrl= 'http://localhost:8080/api/coordinates';
import * as React from 'react';
import PointCanvas from './components/map';
import ReactDOM from 'react-dom';

const mainNode =  document.getElementById('GoogleMap');
ReactDOM.render(
  <PointCanvas url={pointsUrl} pollInterval={2000}/>, mainNode);
