import { Logo, StyledHeader } from './HeaderStyle';
import Link from 'next/link';
import Router from 'next/router';
import NProgress from 'nprogress';


import Nav from '../Nav/Nav';


//Configuring the loading bar
Router.onRouteChangeStart = () => {
  NProgress.start();
};

Router.onRouteChangeComplete = () => {
  NProgress.done();
};

Router.onRouteChangeError = () => {
  NProgress.done();
};


const Header = () => (
  <StyledHeader>
    {/* Sick Fits */}
    <div className="bar">
      <Logo>
        <Link href="/">
          <a>Sick Fits</a>
        </Link>
      </Logo>
      <Nav />
    </div>

    {/* Search */}
    <div className="sub-bar">
      <p>Search</p>
    </div>

    {/* Cart */}
    <div>Cart</div>
  </StyledHeader>
)

export default Header;