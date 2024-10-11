
varying vec2 v_texCoord;

uniform vec2 u_screenSize;
uniform sampler2D u_texture;
uniform float u_time;

void main() {
    vec2 q = gl_FragCoord.xy / u_screenSize.xy;
    vec4 backgroundColor = texture2D(u_texture, v_texCoord);
    backgroundColor.rgb *= 0.5;
    float flash = 0.0;
        if (u_time > 0.0 && u_time < 0.5) {
            flash = 0.5 - u_time;
            backgroundColor.rgb += flash;
    }


    gl_FragColor = backgroundColor;
}