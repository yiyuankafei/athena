package com.application.controller;


import org.springframework.stereotype.Controller;

@Controller
public class PayController {
	
	/**
	 * 支付宝网关
	 */
	public static final String ALI_WEB = "https://openapi.alipay.com/gateway.do";
	
	/**
	 * APP_ID
	 */
	public static final String APP_ID = "2017052507339298";
	
	/**
	 * 应用私钥
	 */
    public static final String RSA2_PRI_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+xJ+RNc8Z8/T4m4aEeTvT23lhPQL44YbcSZSWvWz0ZZjnw4Y/aCdANHyhpuH3NLepkrboVT6guZyd4g8NQKpX7BHEdXt5V1ZwXqh0ci0ive4iBOj8lpj+cxYIQYNOJkbVcR5TFgYxjkyGu7UWDnDq85LkJNEENUe+cs+UV259KVWManIH9EbnYtAxAd2VVD0THc4V1d6T6ARqOsNo9P8LVS6292cCQrx3rRXRor03fMRlmuwgNZyWWGqOeD0uh6aWBU+ecXJ8xVBRHlfp1Jj299IRgYNmATHBq0Rdwf/QwSewREoMYITAKXvhtDrh1hgLl/+0lHzN4JQO7zJPFUiBAgMBAAECggEBALfBflLJmixNqfKvHOwOO2rfRhQ8SNijpNwcpFxvKrxgAOF+nZoGIYjfBg5QSelthlx5TmaNdj4rjIkob4c8etkmNBchc0Z2snSbOXixYnjMqs1qHWLxTX4MLXTWZjyo8iI3TBaMaHrjryM5PD16u5oV9bB9Jjj/eZQh04H4lh1IPka8ViXifU1XBW/qW9VQs2A4e6bz9rkk+G5sk5W9LooIgXRCl4Ixx8fqA0Dhv7qE4L0KHha6BKI7wwqleioBYv+nnC/osX0jwv7B/n1nRmhYWY+dSyQIWW3FIu+JvQ/saO2LKlOC3afJ0u+8dNQFsdPDKWYWy+vyRD/zc1zd7eECgYEA+vilTn18q8UEgXdSlfrY0WzquYLSVDejot6QioVQZC7FTbHkPNIXZ/Ej1RkkfDehw5DU4HV0PQKf72qHeKVTgkmfXbHcujT7vxccyBDZMauq4suqA/fGuupFNjQ6UzllkZTaCwZBs6IdzBaam55rRpVXSZgwbCjE0NlqnVJWeHMCgYEAwpcqd8in7oLNDaakXS3AIUGEK3K8TqV5SZ89VrGt3bIAgOg//AD1/MuhYHg82SrreHg0dy0r/yRgxNKbMtdG+GZwR0fzSF0+IYS7d0JMRBiZQ1SK7GXgalvdgQU9p8Ap8lTzWMF8BJ/hd/RNzYYTTGD7p2CM/6qp00qS8Ltm4jsCgYB+hYFYu3xxX+ZwmkYBJC/QDFec5C3ClTYico9ttd2cU9PV4luMqeFw3ilRUdaJ5MXhQI+BBNVvuHYGja70DGy4HwN/iZ4cJX/QiwdQeOX123RP15rDW1NNgbMYq2XH9QSWSvNNYX8X3x+kdrTExS2RrNIPYPDwZfEq1KCh5ROArwKBgHN8YbDrfnb4XBgg8WV3lJJoHrfmhUmrvVrrTe0Y0FZFoTTgIbE/JL6eX60wTvSYG13JAWJFhdoLzf6h+4fl6fHcYgB7/C/TXt9nthamWzsC2lryXuzD3bdhxAkhgiD/1CVFM5NWaXMZLCgT7uTuEKAA4vnIPIYz1ExY8vI6VmonAoGAS2UZGNh4GYyoVR7zPZT8gglhKuQgy0q4jKFTO0nPgvq0s237JMsfho3gNbMwkGGPPSqYXhE+HjEhabe8J8DYBblDHo7+MSZVcFBPayI2+S/t03mY/Zq0ov7PMYjPM9Xs8iRwdtQSFxcemeK3/b7YO8nzC7RXqKJFcu7WuDAoprk=";

    /**
	 * 应用公钥
	 */
    public static final String RSA2_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvsSfkTXPGfP0+JuGhHk709t5YT0C+OGG3EmUlr1s9GWY58OGP2gnQDR8oabh9zS3qZK26FU+oLmcneIPDUCqV+wRxHV7eVdWcF6odHItIr3uIgTo/JaY/nMWCEGDTiZG1XEeUxYGMY5Mhru1Fg5w6vOS5CTRBDVHvnLPlFdufSlVjGpyB/RG52LQMQHdlVQ9Ex3OFdXek+gEajrDaPT/C1UutvdnAkK8d60V0aK9N3zEZZrsIDWcllhqjng9LoemlgVPnnFyfMVQUR5X6dSY9vfSEYGDZgExwatEXcH/0MEnsERKDGCEwCl74bQ64dYYC5f/tJR8zeCUDu8yTxVIgQIDAQAB";
	
    /**
	 * 数据格式
	 */
	public static final String DATA_FORMAT = "json";
	
	/**
	 * 编码
	 */
	public static final String ENCODING = "UTF-8";
	
	/**
	 * 支付宝公钥
	 */
	public static final String ALI_PUB_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAopjaecegk7uIcfh3xfi1gpZayVN+o8v4F3UZJ3ESoqU4vH2rFOkD+RHC0RFnDt2ibtpMuqms7B8nipWMZeWGv4/oZg3hf7EJowV7v1IdPVLWBkjaAkcPUHJVxnj+kVbfLG4hUtwAwnf5iov1xsbtdoAsdfGXHed2gkD7sqVAOW+bGWRCAkRRcyiB59fSM/4DvnQGxcPC7IVqv7rFlyP+mGNhDQMRO5JiR+U9hYUHtc/vJnKHiGfLHIIq607y5WgUzTWEphNZV6DTk8MyCdjRYVvVBnjjeU0+eBVCP6381V0R0FbeMlZ5L9FgMLZbbzuRfQnfLj4+6CuazLnJhT4xEQIDAQAB";

	/**
	 * 加密方式
	 */
	public static final String SIGN_TYPE = "RSA2";
	
	/**
	 * 
	 * 开发者网关验证服务
	 */
    public static final String ALIPAY_CHECK_SERVICE = "alipay.service.check";

	

}