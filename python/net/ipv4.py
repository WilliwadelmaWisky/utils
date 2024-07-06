SECTION_COUNT: int = 4
SECTION_BITSIZE: int = 8
ADDRESS_BITSIZE: int = 32


def get_subnet_adress(s: str, base: int = 10) -> int:
    """
    Get an adress of a subnet for a certain IPv4 address + mask
    :param s: An IPv4 address + mask, example: 255.255.255.255/24
    :param base: Base of the adress (bin, oct, dec, hex)
    :return: A subnet address as a 32 bit number
    """
    props = s.split('/')
    ip_address = str_to_address(props[0], base)
    subnet_mask = get_subnet_mask(int(props[1]))
    return ip_address & subnet_mask


def get_broadcast_adress(s: str, base: int = 10) -> int:
    """
    Get a broadcast address for a certain IPv4 address + mask
    :param s: An IPv4 address + mask, example: 255.255.255.255/24
    :param base: Base of the adress (bin, oct, dec, hex)
    :return: A broadcast address as a 32 bit number
    """
    props = s.split('/')
    ip_address = str_to_address(props[0], base)
    subnet_mask = get_subnet_mask(int(props[1]))
    return ip_address | ~subnet_mask


def get_subnet_mask(subnet_size: int) -> int:
    """
    Constructs a mask for a subnet of selected size
    :param subnet_size: size of the subnet (0 - 31)
    :return: The mask
    """
    subnet_size = max(min(subnet_size, ADDRESS_BITSIZE - 1), 0)
    subnet_mask = 0

    for i in range(subnet_size):
        subnet_mask |= 1 << (ADDRESS_BITSIZE - (i + 1))

    return subnet_mask


def str_to_address(s: str, base: int = 10) -> int:
    """
    Converts a dot notation string to an IPv4 address
    :param s: An address as a dot notation string, example x.x.x.x
    :param base: Base of the adress (bin, oct, dec, hex)
    :return: Address as a 32 bit number
    """
    address = 0
    for i, s in enumerate(s.split('.')):
        address += int(s, base) << (ADDRESS_BITSIZE - (SECTION_BITSIZE * (i + 1)))

    return address


def address_to_str(adress: int, base: int = 10) -> str:
    """
    Converts an IPv4 address to a dot notation string.
    Supported bases are 2, 8, 16 (all the other bases will return base 10).
    :param adress: An address as a 32 bit number
    :param base: Base of the adress (bin, oct, dec, hex)
    :return: Address as a dot notation string, example: x.x.x.x
    """
    subnet = ""
    for i in range(SECTION_COUNT):
        prop_value = (adress >> (ADDRESS_BITSIZE - (SECTION_BITSIZE * (i + 1)))) & 0xFF
        match base:
            case 2:     prop_str = bin(prop_value)[2:].zfill(8)
            case 8:     prop_str = oct(prop_value)[2:].zfill(4)
            case 16:    prop_str = hex(prop_value)[2:].zfill(2)
            case _:     prop_str = str(prop_value)

        subnet += prop_str
        subnet += '.'

    return subnet[:-1]
