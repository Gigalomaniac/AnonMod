// This shader is created by Simmons.
// All rights reserved.

attribute vec4 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoord0;

uniform mat4 u_projTrans;

varying vec4 v_color;
varying vec2 v_texCoords;

void main() {
    gl_Position = u_projTrans * a_position;
    v_color = a_color;
    v_texCoords = a_texCoord0;
}