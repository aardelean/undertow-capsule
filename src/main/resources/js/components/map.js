'use strict';
import * as React from 'react';
import {GoogleMap, Marker} from "react-google-maps";

class SimpleMap extends React.Component {
  render() {
  return (
      <section style={{height: "100%"}}>
        <GoogleMap containerProps={{
            style: {
              height: "100%",
            },
          }}
          defaultZoom={3}
          defaultCenter={{lat: -25.363882, lng: 131.044922}}
        >
          {this.props.markers.map((marker, index) => {
            return (
              <Marker
                {...marker}
                onRightclick={() => props.onMarkerRightclick(index)} />
            );
          })}
        </GoogleMap>
      </section>
    );
  }
}

export default SimpleMap;
