import Nav from '../Nav/Nav';
import Link from 'next/link';
import { Logo, StyledHeader } from './HeaderStyle';

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