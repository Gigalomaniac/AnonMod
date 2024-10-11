// Created by Reinder Nijhoff 2014
// Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.
// @reindernijhoff
//
// https://www.shadertoy.com/view/Xtf3zn
// @EricBartusch just simplified it to be rain over a background

#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoord;

uniform vec2 u_screenSize;
uniform sampler2D u_texture;
uniform float u_time;


//----------------------------------------------------------------------
// noises

float hash( float n ) {
    return fract(sin(n)*687.3123);
}

float noise( in vec2 x ) {
    vec2 p = floor(x);
    vec2 f = fract(x);
    f = f*f*(3.0-2.0*f);
    float n = p.x + p.y*157.0;
    return mix(mix( hash(n+  0.0), hash(n+  1.0),f.x),
               mix( hash(n+157.0), hash(n+158.0),f.x),f.y);
}

//----------------------------------------------------------------------
// main

void main() {
    vec2 q = gl_FragCoord.xy / u_screenSize.xy;
    vec4 backgroundColor = texture2D(u_texture, v_texCoord);
    backgroundColor.rgb *= 0.8;
	vec2 p = -1.0 + 2.0*q;
    p.x *= u_screenSize.x / u_screenSize.y;

    vec4 col = backgroundColor;

    // Rain (by Dave Hoskins)
    vec2 st = 516. * ( p* vec2(.5, .01)+vec2(u_time*.13-q.y*.6, u_time*.13) );
    float f = noise( st ) * noise( st*0.773) * 1.55;
    f = clamp(pow(abs(f), 13.0) * 13.0, 0.0, q.y*.75);

    col += 0.25*f*(0.5+backgroundColor);

    gl_FragColor = v_color * col;
}
