
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';

export default function Cardgroup() {
    return (
        <>
        <section class="pricing py-5">
        <div class="container">
          <div class="row">
            <App acctype={"FREE"} rate={0} users={'Single User'} storage={5} pvtproject={false} phonesupport={false} subdomain={false}
            monthlyreport={false} />
            <App acctype={"PLUS"} rate={9} users={'5 User'} storage={50} pvtproject={true} phonesupport={true} subdomain={true}
            monthlyreport={false} />
            <App acctype={"PRO"} rate={49} users={'Unlimited Users'} storage={150} pvtproject={true} phonesupport={true} subdomain={true}
            monthlyreport={true} />
                    </div>
                </div>
                </section>
        </>
    );
}
