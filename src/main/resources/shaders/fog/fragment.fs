// Credit to EricB who graciously donated this shader code to me

#ifdef GL_ES
precision lowp float;
#endif

varying vec2 v_texCoord;

uniform vec2 u_screenSize;
uniform sampler2D u_texture;
uniform float u_time;
uniform int u_size;
uniform vec3 u_positions[500];
uniform float u_zoom;
uniform float u_intensity;

const vec3 COLOR = vec3(0.42, 0.40, 0.47); // The color of the fog

float random (vec2 st) {
    return fract(sin(dot(st.xy, vec2(12.9818,79.279)))*43758.5453123);
}

vec2 random2(vec2 st){
    st = vec2( dot(st,vec2(127.1,311.7)), dot(st,vec2(269.5,183.3)) );
    return -1.0 + 2.0 * fract(sin(st) * 7.);
}

float noise(vec2 st) {
    vec2 i = floor(st);
    vec2 f = fract(st);

    // smoothstep
    vec2 u = f*f*(3.0-2.0*f);

    return mix( mix( dot( random2(i + vec2(0.0,0.0) ), f - vec2(0.0,0.0) ),
                     dot( random2(i + vec2(1.0,0.0) ), f - vec2(1.0,0.0) ), u.x),
                mix( dot( random2(i + vec2(0.0,1.0) ), f - vec2(0.0,1.0) ),
                     dot( random2(i + vec2(1.0,1.0) ), f - vec2(1.0,1.0) ), u.x), u.y);
}


float fractal_brownian_motion(vec2 coord) {
    float value = 0.0;
    float scale = 0.2;
    for (int i = 0; i <2; i++) { // Reduced iterations
        value += noise(coord) * scale;
        coord *= 2.0;
        scale *= 0.5;
    }
    return value + 0.2;
}

void main() {
    vec2 st = gl_FragCoord.xy / u_screenSize.xy;
    st *= u_screenSize.xy / u_screenSize.y;

    float finalFog = 1.0;
    for (int i = 0; i < u_size; i++) {
        if (finalFog < 0.06) break; // Early exit if finalFog is small (0.1 has small artifacting for fast mouse movements)

        vec2 correctedMouse = vec2(u_positions[i].x / u_screenSize.x * (u_screenSize.x / u_screenSize.y), u_positions[i].y / u_screenSize.y); // Correct the cursor position for the aspect ratio
        float distance = length(correctedMouse - st);
        float fogFactor = mix(1.0, 0.0, smoothstep(0.0, 0.2, distance)); // Calculate the fog factor based on the distance
        fogFactor *= 1.0 - u_positions[i].z;

        vec2 pos = vec2(st * u_zoom);
	    vec2 motion = vec2(fractal_brownian_motion(pos + vec2(u_time * -0.5, u_time * -0.3)));
	    float fog = fractal_brownian_motion(pos + motion) * u_intensity * (1.0 - fogFactor);
	    finalFog = min(finalFog, fog);
    }

	vec3 BG = texture2D(u_texture, v_texCoord).rgb;
    gl_FragColor = vec4(mix(BG, COLOR, finalFog), 0.25);
}