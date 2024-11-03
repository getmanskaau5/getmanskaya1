from config.config import logger


def check_http_response(response, param_to_chek) -> bool:
    result = None
    try:
        assert param_to_chek in response.text

    except AssertionError as err:
        response.failure(f"Assertion error: text pattrn {param_to_chek} was not foound in response body")
        logger.warning(f"Assersion error: text pattrn {param_to_chek} was not foound in response body ")


        result = False

    else:
        result = True

    finally:
        return result

