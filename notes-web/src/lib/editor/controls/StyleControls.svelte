<script lang="ts">
    import {Button} from 'flowbite-svelte'
    import {LetterBoldOutline, LetterItalicOutline, LetterUnderlineOutline} from 'flowbite-svelte-icons'
    import {editorStore} from "$lib/editor.store";

    const {store: editor} = editorStore

    const size = 'xs'

    const focus = () => $editor.chain().focus();
    const icons = [
        {icon: LetterBoldOutline, handler: () => focus().toggleBold().run()},
        {icon: LetterItalicOutline, handler: () => focus().toggleItalic().run()},
        {icon: LetterUnderlineOutline, handler: () => focus().toggleUnderline().run()}
    ]
</script>

{#each icons as {icon, handler}}
    <Button on:click={handler} color="light" outline={true} size="xs">
        <svelte:component this={icon} size={size}/>
    </Button>
{/each}
