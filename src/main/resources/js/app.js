'use strict';

import * as React from 'react';
import SimpleMapPage from './components/custom-map';
import ReactDOM from 'react-dom';

const mainNode =  document.getElementById('GoogleMap');
ReactDOM.render(
  <SimpleMapPage/>, mainNode);
