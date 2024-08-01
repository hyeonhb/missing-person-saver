// (제보방법) 현재위치로 제보하기

import {useEffect, useState} from 'react';
import './OptionModal.css';
import env from '../../env';

const ReportGPS = ({onSubmit}) => {
  const [marker, setMarker] = useState(null);
  const [location, setLocation] = useState({lat: '', lng: ''});

  useEffect(() => {
    // Kakao 지도 API 스크립트를 동적으로 로드합니다.
    const script = document.createElement('script');
    const MAP_API_KEY = env.KAKAO_MAP_API_KEY;
    script.src = `https://dapi.kakao.com/v2/maps/sdk.js?appkey=${MAP_API_KEY}&autoload=false`;
    script.onload = () => {
      window.kakao.maps.load(() => {
        // 지도를 생성합니다.
        const container = document.getElementById('map-container');
        const options = {
          center: new window.kakao.maps.LatLng(37.56718386576108, 127.00987309483482),
          level: 3
        };
        const map = new window.kakao.maps.Map(container, options);

        // 클릭 이벤트를 등록합니다.
        window.kakao.maps.event.addListener(map, 'click', (mouseEvent) => {
          // 기존 마커가 있으면 제거합니다.
          if (marker) {
            // marker.setMap(null);
          }

          // 클릭한 위치의 위도와 경도를 가져옵니다.
          const latlng = mouseEvent.latLng;
          const lat = latlng.getLat();
          const lng = latlng.getLng();

          // 새 마커를 생성하고 지도의 클릭한 위치에 추가합니다.
          const newMarker = new window.kakao.maps.Marker({
            position: latlng,
            map: map,
          });
          setMarker(newMarker);

          // 마커에 대한 인포윈도우를 생성합니다.
          const infowindow = new window.kakao.maps.InfoWindow({
            content: `<div style="padding:5px;">위도: ${lat} <br> 경도: ${lng}</div>`,
            removable: true
          });
          infowindow.open(map, newMarker);

          setLocation({lat, lng});
        });
      });
    };
    document.head.appendChild(script);

    // 컴포넌트가 언마운트될 때 스크립트를 제거합니다.
    return () => {
      document.head.removeChild(script);
    };
  }, []); // marker 상태를 의존성 배열에 추가하여 업데이트를 반영합니다.

  function bindLocation() {
    if (location.lat === '' || location.lng === '') {
      window.alert('지도에서 위치를 선택해주세요.');
      return;
    }
    onSubmit(location)
  }

  return (
    <div className="option-modal">
      <div className="option-modal-content">
        <div id="map-container" />
        <button onClick={bindLocation}>제보</button>
      </div>
    </div>
  );
};

export default ReportGPS;